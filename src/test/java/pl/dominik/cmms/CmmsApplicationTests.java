package pl.dominik.cmms;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import pl.dominik.cmms.entity.security.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CmmsApplicationTests {

    @Test
    public void contextLoads() {
    }

    //
//    private int port = 8080;
    @Autowired
    private TestRestTemplate restTemplate;

    private String getRootUrl() {
        return "https://vast-wave-10839.herokuapp.com";
    }

    @Test
    public void testCreateUser() {

        String[] names = {"Marek", "Damian", "Mateusz", "Janusz", "Mieczysław", "Krzysztof",
                "Adam", "Jakub"};
        String[] lastnames = {"Add", "Dell", "Hpa", "Grażyna", "Jań", "Kry",
                "Dama", "Kub"};

        int testCase = names.length;
        for (int i = 0; i < testCase; i++) {

            User user = new User();
            user.setFirstName(names[i]);
            user.setLastName(lastnames[i]);
            user.setPassword(names[i]);
            user.setUsername(names[i].substring(0, 1) + lastnames[i]);

            ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/update-user",
                    user, User.class);
            Assert.assertNotNull(postResponse);
            Assert.assertNotNull(postResponse.getBody());
            System.out.println(postResponse);
            System.out.println(postResponse.getBody());
            System.out.println(getRootUrl());
        }
    }

//    @Test
//    public void testCreatePriority() {
//        ProductionBlocked productionBlocked = new ProductionBlocked();
//        productionBlocked.setName("1-Wysoki");
//        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/add-user", user, User.class);
//        Assert.assertNotNull(postResponse);
//        Assert.assertNotNull(postResponse.getBody());
//        }
//    }


//    @Test
//    public void testCreateRole() {
//        Role role = new Role();
//        role.setName("ROLE_MECH");
//        ResponseEntity<Role> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/v1/add-role", role, Role.class);
//        Assert.assertNotNull(postResponse);
//        Assert.assertNotNull(postResponse.getBody());
//
//        Role role1 = new Role();
//        role1.setName("ROLE_KIEROWNIK");
//        ResponseEntity<Role> postResponse1 = restTemplate.postForEntity(getRootUrl() + "/api/v1/add-role", role1, Role.class);
//        Assert.assertNotNull(postResponse1);
//        Assert.assertNotNull(postResponse1.getBody());
//    }

}
