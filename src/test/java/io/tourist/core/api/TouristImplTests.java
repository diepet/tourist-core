package io.tourist.core.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.IAnswer;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.tourist.core.api.Camera;
import io.tourist.core.api.CameraRollFactoryImpl;
import io.tourist.core.api.TouristImpl;
import io.tourist.core.event.ShotPrinterTourEventListener;
import io.tourist.core.event.TourEventListener;

public class TouristImplTests {

	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);

	@Mock
	private ProceedingJoinPoint proceedingJoinPointDummyMethod;

	@Mock
	private Signature signatureDummyMethod;

	@Mock
	private ProceedingJoinPoint proceedingJoinPointInnerDummyMethod;

	@Mock
	private Signature signatureInnerDummyMethod;

	private TouristImpl tourist;

	private ShotPrinterTourEventListener shotPrinterTourEventListener;

	private ByteArrayOutputStream baos;

	@Before
	public void setUp() throws Throwable {
		tourist = new TouristImpl();
		tourist.setCameraRollFactory(new CameraRollFactoryImpl());
		Set<TourEventListener> listeners = new LinkedHashSet<TourEventListener>();
		baos = new ByteArrayOutputStream();
		shotPrinterTourEventListener = new ShotPrinterTourEventListener(baos);
		listeners.add(shotPrinterTourEventListener);
		tourist.setTourEventListenerSet(listeners);

		// global mock configuration
		// configure mocks
		EasyMock.reset(this.proceedingJoinPointDummyMethod, this.signatureDummyMethod,
				this.proceedingJoinPointInnerDummyMethod, this.signatureInnerDummyMethod);

		EasyMock.expect(this.proceedingJoinPointDummyMethod.getSignature()).andReturn(this.signatureDummyMethod)
				.anyTimes();
		EasyMock.expect(this.proceedingJoinPointInnerDummyMethod.getSignature())
				.andReturn(this.signatureInnerDummyMethod).anyTimes();
		EasyMock.expect(this.signatureDummyMethod.getName()).andReturn("dummyMethod").anyTimes();
		EasyMock.expect(this.signatureInnerDummyMethod.getName()).andReturn("innerDummyMethod").anyTimes();
	}

	@Test
	public void testSingleProceedCall() throws Throwable {

		// configure proceed() mock call
		EasyMock.expect(this.proceedingJoinPointDummyMethod.proceed()).andAnswer(new IAnswer<Object>() {

			@Override
			public Object answer() throws Throwable {
				Camera camera = tourist.getCamera();
				Assert.assertNotNull(camera);
				Assert.assertTrue(camera.isOn());
				camera.shot("Some shot");
				return null;
			}
		}).anyTimes();
		EasyMock.replay(this.proceedingJoinPointDummyMethod, this.signatureDummyMethod);

		// assert camera is off
		Camera camera = this.tourist.getCamera();
		Assert.assertNotNull(camera);
		Assert.assertFalse(camera.isOn());

		// invoke aroundPointcut in order to start a travel
		this.tourist.aroundPointcut(this.proceedingJoinPointDummyMethod);

		// assert camera is off after travel completation
		camera = this.tourist.getCamera();
		Assert.assertNotNull(camera);
		Assert.assertFalse(camera.isOn());

		// travel assertions
		String[] lines = baos.toString().split(String.format("%n"));
		Assert.assertEquals(4, lines.length);
		Assert.assertEquals("-- START TRAVEL --", lines[0]);
		Assert.assertEquals("dummyMethod():", lines[1]);
		Assert.assertEquals("\tSHOT #1: Some shot", lines[2]);
		Assert.assertEquals("-- END TRAVEL --", lines[3]);

	}

	@Test
	public void testMultipleProceedCall() throws Throwable {

		// configure proceed() mock call
		EasyMock.expect(this.proceedingJoinPointDummyMethod.proceed()).andAnswer(new IAnswer<Object>() {

			@Override
			public Object answer() throws Throwable {
				Camera camera = tourist.getCamera();
				Assert.assertNotNull(camera);
				Assert.assertTrue(camera.isOn());
				camera.shot("First shot");
				tourist.aroundPointcut(proceedingJoinPointInnerDummyMethod);
				camera.shot("Third shot");
				return null;
			}
		}).anyTimes();
		// configure proceed() mock call
		EasyMock.expect(this.proceedingJoinPointInnerDummyMethod.proceed()).andAnswer(new IAnswer<Object>() {

			@Override
			public Object answer() throws Throwable {
				Camera camera = tourist.getCamera();
				Assert.assertNotNull(camera);
				Assert.assertTrue(camera.isOn());
				camera.shot("Second shot");
				return null;
			}
		}).anyTimes();
		EasyMock.replay(this.proceedingJoinPointDummyMethod, this.signatureDummyMethod,
				this.proceedingJoinPointInnerDummyMethod, this.signatureInnerDummyMethod);

		// assert camera is off
		Camera camera = this.tourist.getCamera();
		Assert.assertNotNull(camera);
		Assert.assertFalse(camera.isOn());

		// invoke aroundPointcut in order to start a travel
		this.tourist.aroundPointcut(this.proceedingJoinPointDummyMethod);

		// assert camera is off after travel completation
		camera = this.tourist.getCamera();
		Assert.assertNotNull(camera);
		Assert.assertFalse(camera.isOn());

		// travel assertions
		String[] lines = baos.toString().split(String.format("%n"));
		Assert.assertEquals(7, lines.length);
		Assert.assertEquals("-- START TRAVEL --", lines[0]);
		Assert.assertEquals("dummyMethod():", lines[1]);
		Assert.assertEquals("\tinnerDummyMethod():", lines[2]);
		Assert.assertEquals("\t\tSHOT #1: Second shot", lines[3]);
		Assert.assertEquals("\tSHOT #1: First shot", lines[4]);
		Assert.assertEquals("\tSHOT #2: Third shot", lines[5]);
		Assert.assertEquals("-- END TRAVEL --", lines[6]);

	}

	@Test
	public void testSingleProceedCallFailed() throws Throwable {

		// configure proceed() mock call
		EasyMock.expect(this.proceedingJoinPointDummyMethod.proceed()).andAnswer(new IAnswer<Object>() {

			@Override
			public Object answer() throws Throwable {
				Camera camera = tourist.getCamera();
				Assert.assertNotNull(camera);
				Assert.assertTrue(camera.isOn());
				camera.shot("Some shot");
				throw new IOException("IO error mock");
			}
		}).anyTimes();
		EasyMock.replay(this.proceedingJoinPointDummyMethod, this.signatureDummyMethod);

		// assert camera is off
		Camera camera = this.tourist.getCamera();
		Assert.assertNotNull(camera);
		Assert.assertFalse(camera.isOn());

		// invoke aroundPointcut in order to start a travel that will fail
		try {
			this.tourist.aroundPointcut(this.proceedingJoinPointDummyMethod);
			Assert.fail("Previous method call should raise an IOException");
		} catch (Throwable e) {
			Assert.assertTrue(e instanceof IOException);
			Assert.assertEquals("IO error mock", e.getMessage());
		}

		// assert camera is off after travel completation
		camera = this.tourist.getCamera();
		Assert.assertNotNull(camera);
		Assert.assertFalse(camera.isOn());

		// travel assertions
		String[] lines = baos.toString().split(String.format("%n"));
		Assert.assertEquals(4, lines.length);
		Assert.assertEquals("-- START TRAVEL --", lines[0]);
		Assert.assertEquals("dummyMethod():", lines[1]);
		Assert.assertEquals("\tEXCEPTION THROWN: java.io.IOException", lines[2]);
		Assert.assertEquals("-- END TRAVEL --", lines[3]);

	}

	@Test
	public void testMultipleProceedCallFailed() throws Throwable {

		// configure proceed() mock call
		EasyMock.expect(this.proceedingJoinPointDummyMethod.proceed()).andAnswer(new IAnswer<Object>() {

			@Override
			public Object answer() throws Throwable {
				Camera camera = tourist.getCamera();
				Assert.assertNotNull(camera);
				Assert.assertTrue(camera.isOn());
				camera.shot("First shot");
				tourist.aroundPointcut(proceedingJoinPointInnerDummyMethod);
				camera.shot("Third shot");
				return null;
			}
		}).anyTimes();
		// configure proceed() mock call
		EasyMock.expect(this.proceedingJoinPointInnerDummyMethod.proceed()).andAnswer(new IAnswer<Object>() {

			@Override
			public Object answer() throws Throwable {
				Camera camera = tourist.getCamera();
				Assert.assertNotNull(camera);
				Assert.assertTrue(camera.isOn());
				camera.shot("Second shot");
				throw new NullPointerException("a dummy null exception");
			}
		}).anyTimes();
		EasyMock.replay(this.proceedingJoinPointDummyMethod, this.signatureDummyMethod,
				this.proceedingJoinPointInnerDummyMethod, this.signatureInnerDummyMethod);

		// assert camera is off
		Camera camera = this.tourist.getCamera();
		Assert.assertNotNull(camera);
		Assert.assertFalse(camera.isOn());

		// invoke aroundPointcut in order to start a travel that will fail
		try {
			this.tourist.aroundPointcut(this.proceedingJoinPointDummyMethod);
			Assert.fail("Previous method call should raise a NullPointerException");
		} catch (Throwable e) {
			Assert.assertTrue(e instanceof NullPointerException);
			Assert.assertEquals("a dummy null exception", e.getMessage());
		}
		// assert camera is off after travel completation
		camera = this.tourist.getCamera();
		Assert.assertNotNull(camera);
		Assert.assertFalse(camera.isOn());

		// travel assertions
		String[] lines = baos.toString().split(String.format("%n"));
		Assert.assertEquals(6, lines.length);
		Assert.assertEquals("-- START TRAVEL --", lines[0]);
		Assert.assertEquals("dummyMethod():", lines[1]);
		Assert.assertEquals("\tinnerDummyMethod():", lines[2]);
		Assert.assertEquals("\t\tEXCEPTION THROWN: java.lang.NullPointerException", lines[3]);
		Assert.assertEquals("\tEXCEPTION THROWN: java.lang.NullPointerException", lines[4]);
		Assert.assertEquals("-- END TRAVEL --", lines[5]);

	}

}
