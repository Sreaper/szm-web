package user_cms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lm.entity.UserEntity;
import com.lm.service.UserService;

public class UserServiceTest extends BaseSpringJunit{

	private static final String TESTID = "1";
	
	@Autowired
	UserService userService;
	
	@Before
	public void init(){
		
	}
	
	@Test
	public void testGetUserEntityById(){
		UserEntity userEntity = userService.getUserEntityById(TESTID);
		System.out.println(userEntity.toString());
		Assert.assertNotNull(userEntity);
	}
}
