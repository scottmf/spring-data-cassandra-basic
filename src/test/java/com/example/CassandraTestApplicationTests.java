package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.example.persist.dao.UserRepository;
import com.example.persist.shared.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CassandraTestApplication.class)
public class CassandraTestApplicationTests {

    @Autowired
    private UserRepository userRepository;

	@Test
    @Rollback
    @Transactional
	public void contextLoads() {
        Iterable<User> all = userRepository.findAll();
        all.forEach(user -> assertNotNull(user));
        User user = new User();
        user.setFirstName("kvody-flynn");
        user.setLastName("copper");
        user.setId(0l);
        userRepository.save(user);
        User u = userRepository.findByFirstName("kvody-flynn");
        assertEquals(u, user);
	}

}
