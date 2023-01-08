package iinterface;

import java.util.ArrayList;
import java.util.Scanner;

public interface ISort<E> {
    E findById(Scanner scanner,String pattern );
    boolean checkIdToList(int id, ArrayList<E> list);
}
