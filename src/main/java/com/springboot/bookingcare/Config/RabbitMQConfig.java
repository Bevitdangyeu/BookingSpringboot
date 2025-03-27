package com.springboot.bookingcare.Config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    // tên của hàng đợi
    public static final String QUEUE_NAME="appointment_queue";
    public static final String SEND_MAIL="mail_queue";
    public static final String EXCHANGE_NAME="main_exchange";
    // cấu hình chuyển message từ object java-> json và ngược lại
    @Bean
    public MessageConverter jsonMessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        // Chỉ định package được phép deserialize
        typeMapper.setTrustedPackages("com.springboot.bookingcare.DTO");
        converter.setJavaTypeMapper(typeMapper);
        return converter;
    }
    // câ hình tạo queue theo tên đã được chỉ định
    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME,true);
    }
    @Bean
    public Queue send_mail(){
        return new Queue(SEND_MAIL,true);
    }
    // tạo exchange
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(EXCHANGE_NAME);
    }
    // tạo binding, tạo routing key cho queue
    @Bean
    public Binding appointmentBinding(Queue queue){
        return BindingBuilder.bind(queue).to(directExchange()).with("appointment_routing");
    }
    @Bean
    public Binding emailBinding(@Qualifier("send_mail") Queue sendMailQueue){
        return BindingBuilder.bind(sendMailQueue).to(directExchange()).with("email_routing");
    }
    // dùng để gửi message đến rabbit
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        // kết nối đến rabbit thông qua connectionFactory
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        // chuyển đổi dữ liệu thành json trước khi gửi
        rabbitTemplate.setMessageConverter(jsonMessageConverter()); // Sử dụng JSON Converter
        return rabbitTemplate;
    }
    // cấu hình để nhận tin nhắn từ rabbit
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // chuyển đổi json về java object
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
    //dùng để quản lý các queue
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
}
