package sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleWebsocketApplication.class)
@WebAppConfiguration
@TestPropertySource(properties = "sample.zipkin.enabled=false")
public class SampleWebsocketApplicationTests {

	@Test
	public void contextLoads() {
	}

}
