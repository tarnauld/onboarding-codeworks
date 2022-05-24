package com.codeworks.onboarding.use_cases.users;

import com.codeworks.onboarding.infrastructure.users.UserEntity;
import com.codeworks.onboarding.infrastructure.users.UserService;
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

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class UserResourceTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UsersResource userResource;

    private LocalDate birthDate;

    @Before
    public void setUp() {
        this.birthDate = LocalDate.now();
    }

    @Test
    public void should_get_users() {
        when(userService.getUsers()).thenReturn(buildUsers());

        List<UserEntity> users = userResource.getUsers();
        UserEntity user = users.get(0);

        Assert.assertEquals("John", user.getName());
        Assert.assertEquals(Long.valueOf(1L), user.getId());
        Assert.assertEquals(birthDate, user.getBirthDate());

        Assert.assertEquals(4, users.size());
    }

    @Test
    public void should_save_user() {
        when(userService.create(Mockito.any())).thenReturn(UserEntity.builder().id(1L).name("John").birthDate(LocalDate.now()).build());

        UserEntity user = userResource.createUser(UserEntity.builder().name("John").birthDate(LocalDate.now()).build());

        Assert.assertEquals(Long.valueOf(1L), user.getId());
        Assert.assertEquals("John", user.getName());
    }

    @Test
    public void should_delete_user() {
        when(userService.deleteUser(Mockito.anyLong())).thenReturn(UserEntity.builder().build());

        UserEntity user = userResource.deleteUser(1L);

        Assert.assertNull(user.getName());
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
