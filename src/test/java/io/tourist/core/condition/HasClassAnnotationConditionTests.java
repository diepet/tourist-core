package io.tourist.core.condition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.aspectj.lang.ProceedingJoinPoint;
import org.easymock.EasyMock;
import org.easymock.EasyMockRule;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class HasClassAnnotationConditionTests {

	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);

	@Mock
	private ProceedingJoinPoint proceedingJoinPointDummyMethod;

	@Test
	public void testHasClassAnnotation() {
		final AnnotatedClass instance = new AnnotatedClass();
		EasyMock.reset(this.proceedingJoinPointDummyMethod);
		EasyMock.expect(this.proceedingJoinPointDummyMethod.getTarget()).andReturn(instance).anyTimes();
		EasyMock.replay(this.proceedingJoinPointDummyMethod);

		HasClassAnnotationCondition condition = new HasClassAnnotationCondition();
		condition.setClazz(DummyAnnotation.class);
		Assert.assertTrue(condition.check(this.proceedingJoinPointDummyMethod));
	}

	@Test
	public void testNotHasClassAnnotation() {
		final NotAnnotatedClass instance = new NotAnnotatedClass();
		EasyMock.reset(this.proceedingJoinPointDummyMethod);
		EasyMock.expect(this.proceedingJoinPointDummyMethod.getTarget()).andReturn(instance).anyTimes();
		EasyMock.replay(this.proceedingJoinPointDummyMethod);

		HasClassAnnotationCondition condition = new HasClassAnnotationCondition();
		condition.setClazz(DummyAnnotation.class);
		Assert.assertFalse(condition.check(this.proceedingJoinPointDummyMethod));
	}

	@Target(ElementType.TYPE)
	@Retention(RetentionPolicy.RUNTIME)
	@interface DummyAnnotation {

	}

	@DummyAnnotation
	class AnnotatedClass {

	}

	class NotAnnotatedClass {

	}

}
