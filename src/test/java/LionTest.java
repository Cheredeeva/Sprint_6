import com.example.Feline;
import com.example.Lion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Mockito;

public class LionTest {

    @Mock
    Feline feline;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @ParameterizedTest
    @CsvSource({
            "Самец, false",
            "Самка, false",
            "Лев, true"
    })
    public void constructTest(String sex, boolean isExceptionThrown) {
        Executable construct = () -> new Lion(sex, feline);
        if (isExceptionThrown) {
            Assertions.assertThrows(Exception.class, construct);
        } else {
            Assertions.assertDoesNotThrow(construct);
        }
    }

    @Test
    public void getKittensTest() throws Exception {
        Lion lion = new Lion("Самец", feline);
        lion.getKittens();
        Mockito.verify(feline, Mockito.times(1)).getKittens();
    }

    @ParameterizedTest
    @CsvSource({
            "Самец, true",
            "Самка, false",
    })
    public void doesHaveManeTest(String sex, boolean hasMane) throws Exception {
        Lion lion = new Lion(sex, feline);
        Assertions.assertEquals(hasMane, lion.doesHaveMane());
    }

    @Test
    public void getFoodTest() throws Exception {
        Lion lion = new Lion("Самец", feline);
        lion.getFood();
        Mockito.verify(feline, Mockito.times(1)).getFood("Хищник");
    }
}
