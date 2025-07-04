import org.example.iterator.ObjectCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Тесты итератора и работы с коллекцией. Не выбрасывают исключений.
 * @author Nikita Bochkov
 * */
class ObjectIteratorTest {

    @Test
    void testIterator_withDefaultObjectsNotThrows_andCheckEqualsOrigArrayAndIteratorArray() {
        ObjectCollection<Integer> objectCollection = createInstanceWithObjects(1000);
        Iterator<Integer> iterator = objectCollection.createIterator();
        Assertions.assertDoesNotThrow(() -> {
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        });
        checkEqualsCollection(objectCollection);
    }

    @Test
    void testIterator_withEditableCollection_doesThrowIllegalStateException() {
        ObjectCollection<Integer> objectCollection = createInstanceWithObjects(1);
        Iterator<Integer> iterator = objectCollection.createIterator();

        Assertions.assertThrows(IllegalStateException.class, () -> {
            int index = 0;
            while (iterator.hasNext()) {
                if (index == 0) {
                    objectCollection.remove(index);
                }
                iterator.next();
                index++;
            }
        });

    }

    @Test
    void testRemoveObjectCollection_withThrowIndexOutOfBoundsException() {
        ObjectCollection<Integer> objectCollection = createInstanceWithObjects(100);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> objectCollection.remove(-10));
    }

    void checkEqualsCollection(ObjectCollection<Integer> result) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> compareList = createCompareArrayList(result.size());
        Iterator<Integer> iterator = result.createIterator();

        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        Assertions.assertArrayEquals(list.toArray(), compareList.toArray());


    }


    ObjectCollection<Integer> createInstanceWithObjects(int count) {
        ObjectCollection<Integer> objectCollection = new ObjectCollection<>();
        Assertions.assertDoesNotThrow(() -> {
            for (int i = 0; i < count; i++) {
                objectCollection.addObject(i);
            }
        });
        return objectCollection;
    }

    ArrayList<Integer> createCompareArrayList(int count) {
        ArrayList<Integer> copy = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            copy.add(i);
        }
        return copy;
    }

}
