import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class TestDemoJUnitTest {

private TestDemo testDemo;	
	
	@BeforeEach
	void setUp() {
		testDemo = new TestDemo();
	}

	@ParameterizedTest
	@MethodSource("TestDemoJUnitTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
if(!expectException) {
	assertThat(testDemo.addPositive(a,b)).isEqualTo(expected);
} else {
	org.assertj.core.api.Assertions.assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
}
	}

	public static Stream<Arguments> argumentsForAddPositive() {
		// @formatter:off
		return Stream.of(
				arguments(2,4,6,false));
		// @formatter:on
	}
	
	@Test
	void assertThatPairsOfPositiveNumbersAreAddedCorrectly() {
		assertThat(testDemo.addPositive(4, 5)).isEqualTo(9);
		assertThat(testDemo.addPositive(40, 50)).isEqualTo(90);
	}
	
	@ParameterizedTest
	@MethodSource("TestDemoJUnitTest#argumentsForPositiveProduct")
	void assertThatTwoPositiveNumbersMultipliedCorrectly(int c, int d, int expected, boolean expectException) {
		if(!expectException) {
			assertThat(testDemo.positiveProduct(c,d)).isEqualTo(expected);
		} else
			org.assertj.core.api.Assertions.assertThatThrownBy(() -> testDemo.positiveProduct(c,d)).isInstanceOf(IllegalArgumentException.class);
	}
	
public static Stream<Arguments> argumentsForPositiveProduct() {
	// @formatter:off
	return Stream.of(
			arguments(3,5,15,false)
			);
	// @formatter:on
}
	@Test
	void assertThatPairsofPositiveNumbersMultipliedCorrectly() {
		assertThat(testDemo.positiveProduct(5, 5)).isEqualTo(25);
	}
	
	@Test
	void assertThatNumbersSquaredCorrectly() {
		TestDemo mockDemo = spy(testDemo);
		doReturn(5).when(mockDemo).getRandomInt();
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
	}
}
