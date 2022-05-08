package real.world.data.pipelines.parser;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class ConverterConfig {
    private final ListableBeanFactory beanFactory;

    public ConverterConfig(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * without spring-boot-starter-web, no custom mappers will be added automatically;
     * register manually we register manually all our {@link Converter}
     */
    @Bean
    public ConversionService conversionService() {
        LoggerFactory.getLogger(ConverterConfig.class).info("ConversionService bean init");
        final FormattingConversionService service = new DefaultFormattingConversionService();
        ApplicationConversionService.addBeans(service, this.beanFactory);
        return service;
    }
}