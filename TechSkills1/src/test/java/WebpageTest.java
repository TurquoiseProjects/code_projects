import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class WebpageTest {

	@Test
	public void testMyUrlLanding() {
		Webpage w = new Webpage("/section/index.html");
		Assert.assertTrue(w.showHighlight("/section/page.html"));
		Assert.assertTrue(w.showHighlight("/section/subsection/index.html"));
		Assert.assertTrue(w.showHighlight("/section/subsection/page.html"));
	}
	
	@Test
	public void testNotLandingNotEqual() {
		Webpage w = new Webpage("/section/page.html");
		Assert.assertFalse(w.showHighlight("/section/other-page.html"));
	}
	
	@Test
	public void testingLandingNotEqual() {
		Webpage w = new Webpage("/section/subsection/index.html");
		Assert.assertFalse(w.showHighlight("/section/other/index.html"));
	}

}
