package com.github.souzafcharles.investmentaggregator.service;

import com.github.souzafcharles.investmentaggregator.model.entity.User;
import com.github.souzafcharles.investmentaggregator.model.entity.dto.CreateUserDTO;
import com.github.souzafcharles.investmentaggregator.model.entity.dto.UpdateUserDTO;
import com.github.souzafcharles.investmentaggregator.model.entity.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Nested
    class createUser {

        @Test
        @DisplayName("Should create a User with success")
        void shouldCreateUserWithSuccess() {

            //Arrange
            var user = new User(UUID.randomUUID(), "username", "email@email.com", "password", Instant.now(), null);
            doReturn(user).when(repository).save(userArgumentCaptor.capture());
            var input = new CreateUserDTO("username", "email@email.com", "password");

            //Act
            var output = service.createUser(input);

            //Assert
            assertNotNull(output);
            var userCaptured = userArgumentCaptor.getValue();
            assertEquals(input.username(), userCaptured.getUsername());
            assertEquals(input.email(), userCaptured.getEmail());
            assertEquals(input.password(), userCaptured.getPassword());

        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowExceptionWhenErrorOccurs() {

            //Arrange
            doThrow(new RuntimeException()).when(repository).save((userArgumentCaptor.capture()));
            var input = new CreateUserDTO("username", "email@email.com", "password");

            //Act and Assert
            assertThrows(RuntimeException.class, () -> service.createUser(input));

        }

    }

    @Nested
    class findUserById {

        @Test
        @DisplayName("Should find a User by Id with success")
        void shouldFindUserByIdWithSuccess() {
            // Arrange
            var id = UUID.randomUUID();
            var user = new User(id, "username", "email@email.com", "password", Instant.now(), Instant.now());
            doReturn(java.util.Optional.of(user)).when(repository).findById(id);

            // Act
            var result = service.getUserById(id.toString());

            // Assert
            assertTrue(result.isPresent());
            assertEquals("username", result.get().getUsername());
        }

        @Test
        @DisplayName("Should return empty when user not found")
        void shouldReturnEmptyWhenUserNotFound() {
            // Arrange
            var id = UUID.randomUUID();
            doReturn(java.util.Optional.empty()).when(repository).findById(id);

            // Act
            var result = service.getUserById(id.toString());

            // Assert
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    class listUsers {

        @Test
        @DisplayName("Should return a list of users with success")
        void shouldReturnListOfUsersWithSuccess() {
            // Arrange
            var userList = List.of(
                    new User(UUID.randomUUID(), "user1", "user1@email.com", "pass1", Instant.now(), Instant.now()),
                    new User(UUID.randomUUID(), "user2", "user2@email.com", "pass2", Instant.now(), Instant.now())
            );
            doReturn(userList).when(repository).findAll();

            // Act
            var result = service.listUsers();

            // Assert
            assertEquals(2, result.size());
        }

        @Test
        @DisplayName("Should return empty list when no users found")
        void shouldReturnEmptyListWhenNoUsersFound() {
            // Arrange
            doReturn(List.of()).when(repository).findAll();

            // Act
            var result = service.listUsers();

            // Assert
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    class updateUserById {

        @Test
        @DisplayName("Should update an existing user successfully")
        void shouldUpdateUserSuccessfully() {
            // Arrange
            var id = UUID.randomUUID();
            var existingUser = new User(id, "oldUsername", "email@email.com", "oldPass", Instant.now(), Instant.now());
            var dto = new UpdateUserDTO("newUsername", "newPass");
            doReturn(java.util.Optional.of(existingUser)).when(repository).findById(id);
            doReturn(existingUser).when(repository).save(userArgumentCaptor.capture());

            // Act
            service.updateUserById(id.toString(), dto);

            // Assert
            var userCaptured = userArgumentCaptor.getValue();
            assertEquals("newUsername", userCaptured.getUsername());
            assertEquals("newPass", userCaptured.getPassword());
        }

        @Test
        @DisplayName("Should not update when user not found")
        void shouldNotUpdateWhenUserNotFound() {
            // Arrange
            var id = UUID.randomUUID();
            var dto = new UpdateUserDTO("newUsername", "newPass");
            doReturn(java.util.Optional.empty()).when(repository).findById(id);

            // Act (no exception expected, just silent failure)
            service.updateUserById(id.toString(), dto);

            // Assert: just verifying save is never called
            org.mockito.Mockito.verify(repository, org.mockito.Mockito.never()).save(org.mockito.Mockito.any());
        }
    }

    @Nested
    class deleteUserById {

        @Test
        @DisplayName("Should delete a user by id successfully")
        void shouldDeleteUserByIdSuccessfully() {
            // Arrange
            var id = UUID.randomUUID();
            doReturn(true).when(repository).existsById(id);

            // Act
            service.deleteById(id.toString());

            // Assert
            org.mockito.Mockito.verify(repository).deleteById(id);
        }

        @Test
        @DisplayName("Should not delete when user not found")
        void shouldNotDeleteWhenUserNotFound() {
            // Arrange
            var id = UUID.randomUUID();
            doReturn(false).when(repository).existsById(id);

            // Act
            service.deleteById(id.toString());

            // Assert
            org.mockito.Mockito.verify(repository, org.mockito.Mockito.never()).deleteById(id);
        }
    }

}