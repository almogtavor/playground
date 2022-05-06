package mapstruct;

import org.mapstruct.extensions.spring.converter.ConversionServiceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import mapstruct.car.Car;
import mapstruct.car.CarDto;
import mapstruct.car.SeatConfiguration;
import mapstruct.car.SeatMaterial;

import javax.annotation.PostConstruct;

@Component
public class MyMapper {
    private static final Logger log = LoggerFactory.getLogger(Start.class);
    private final ConversionServiceAdapter adapter;

    public MyMapper(ConversionServiceAdapter adapter) {
        this.adapter = adapter;
    }

    @PostConstruct
    public void commandLineRunner(ConversionServiceAdapter adapter) {
        final SeatConfiguration seatConf = new SeatConfiguration();
        seatConf.setSeatMaterial(SeatMaterial.FABRIC);
        final Car car = new Car();
        car.setMake("make");
        car.setSeatConfiguration(seatConf);

        log.info("map start");
        final CarDto carDto = adapter.mapCarToCarDto(car);
        log.info(carDto.toString());
    }
}
