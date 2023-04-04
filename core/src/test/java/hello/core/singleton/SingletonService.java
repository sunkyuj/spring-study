package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() { // instance 쓰고싶으면 항상 이 getInstance를 호출해야만 쓸수있음, new 써서 사용 못함
        return instance;
    }

    private SingletonService() { // private 생성자

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}


