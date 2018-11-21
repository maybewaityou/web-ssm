package proxy;

public class UserDaoTest implements DaoTest {

    @Override
    public void save() {
        System.out.println("UserDaoTest save...");
        throw new RuntimeException("Boring Exception...");
    }

}
