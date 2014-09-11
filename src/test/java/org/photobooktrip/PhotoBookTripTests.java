package org.photobooktrip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


@WebAppConfiguration
@ActiveProfiles(value = "test")
@Transactional
@TransactionConfiguration(defaultRollback = true)
@RunWith(SpringJUnit4ClassRunner.class)    
@SpringApplicationConfiguration(classes = Application.class)    
public class PhotoBookTripTests  {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void contextLoads() {
		Assert.assertNotNull(entityManager);
	}

}
