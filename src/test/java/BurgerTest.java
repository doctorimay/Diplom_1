import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;


import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient firstIngredient;
    @Mock
    Ingredient secondIngredient;

    @Test
    public void setBunTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        assertEquals(true,burger.ingredients.contains(firstIngredient));
    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.removeIngredient(0);
        assertEquals(false, burger.ingredients.contains(firstIngredient));
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(1,0);
        assertEquals(false, burger.ingredients.indexOf(firstIngredient)==0);
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        float mockIngridientPrice = 550;
        float mockBunPrice = 60;
        float mockPriceSum = mockIngridientPrice + mockBunPrice + mockBunPrice;
        Mockito.when(firstIngredient.getPrice()).thenReturn(mockIngridientPrice);
        Mockito.when(bun.getPrice()).thenReturn(mockBunPrice);
        float actualSum = burger.getPrice();
        assertEquals(mockPriceSum, actualSum, 0);
    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.setBuns(bun);
        String mockFirstEngredient = "Котлета";
        String mockSecondEngredient = "Соус";
        String bunName = "Булочка";
        float mockFirstEngredientPrice = 200;
        float mockSecondEngredientPrice = 140;
        float mockBunPrice = 120;
        Mockito.when(firstIngredient.getPrice()).thenReturn(mockFirstEngredientPrice);
        Mockito.when(secondIngredient.getPrice()).thenReturn(mockSecondEngredientPrice);
        Mockito.when(bun.getPrice()).thenReturn(mockBunPrice);
        Mockito.when(firstIngredient.getName()).thenReturn(mockFirstEngredient);
        Mockito.when(secondIngredient.getName()).thenReturn(mockSecondEngredient);
        Mockito.when(secondIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(firstIngredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(bun.getName()).thenReturn(bunName);
        String actualReceipt = burger.getReceipt();
        String expectedReceipt =    "(==== Булочка ====)\r\n" +
                "= filling Котлета =\r\n" +
                "= sauce Соус =\r\n" +
                "(==== Булочка ====)\r\n" +
                "\r\n" +
                "Price: 580,000000\r\n";
        assertEquals(expectedReceipt, actualReceipt);
    }


    }
