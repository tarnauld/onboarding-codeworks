package com.codeworks.onboarding.infrastructure.users;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private LocalDate birthDate;

    @Before
    public void setUp() {
        this.birthDate = LocalDate.now();
    }

    @Test
    public void should_get_users() {
        when(userRepository.findAll()).thenReturn(buildUsers());

        List<UserEntity> users = userService.getUsers();
        UserEntity user = users.get(0);

        Assert.assertEquals("John", user.getName());
        Assert.assertEquals(Long.valueOf(1L), user.getId());
        Assert.assertEquals(birthDate, user.getBirthDate());
        Assert.assertEquals(4, users.size());
    }

    @Test
    public void should_get_user_by_id() {
        when(userRepository.findById(Mockito.anyLong()))
                .thenReturn(UserEntity.builder().id(1L).name("John").birthDate(LocalDate.now()).build());

        UserEntity user = userService.findUserBy(1L);

        Assert.assertEquals(Long.valueOf(1L), user.getId());
        Assert.assertEquals("John", user.getName());
    }

    @Test
    public void should_save_user() {
        when(userRepository.save(Mockito.any())).thenReturn(UserEntity.builder().id(1L).name("John").birthDate(LocalDate.now()).build());

        UserEntity user = userService.create(UserEntity.builder().name("John").birthDate(LocalDate.now()).build());

        Assert.assertEquals(Long.valueOf(1L), user.getId());
        Assert.assertEquals("John", user.getName());
    }

    @Test
    public void should_delete_user() {
        when(userRepository.deleteById(Mockito.anyLong())).thenReturn(UserEntity.builder().build());

        UserEntity user = userService.deleteUser(1L);

        Assert.assertNull(user.getName());
    }

    @Test
    public void should_find_user_by_name() {
        when(userRepository.findByName(anyString())).thenReturn(UserEntity.builder()
                .id(1L)
                .name("John")
                .birthDate(LocalDate.now())
                .build());

        UserEntity user = userService.findUserByName("John");

        Assert.assertEquals("John", user.getName());
        Assert.assertEquals(Long.valueOf(1L), user.getId());
    }

    private List<UserEntity> buildUsers() {
        return Arrays.asList(
                UserEntity.builder().id(1L).name("John").birthDate(birthDate).build(),
                UserEntity.builder().id(2L).name("Clara").birthDate(birthDate).build(),
                UserEntity.builder().id(3L).name("Bertrand").birthDate(birthDate).build(),
                UserEntity.builder().id(4L).name("Alice").birthDate(birthDate).build()
        );
    }
}
