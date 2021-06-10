package blowest.kiosk.util;

import blowest.kiosk.entity.Store;
import blowest.kiosk.repository.StoreRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements ApplicationRunner {

    private final StoreRepository storeRepository;

    public DataGenerator(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        storeRepository.save(new Store("버거킹", true));
        storeRepository.save(new Store("맥도날드", true));
        storeRepository.save(new Store("롯데리아", true));
        storeRepository.save(new Store("맘스터치", true));
    }
}
