package user_cms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:/config/applicationContext-user.xml")
@Transactional
public class BaseSpringJunit {

	
	@Test
	public void initEmpty(){
		
	}
}
