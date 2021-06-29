package com.paulocoimbra.springboot;

import com.paulocoimbra.springboot.domain.*;
import com.paulocoimbra.springboot.domain.enums.ClientType;
import com.paulocoimbra.springboot.domain.enums.PaymentStatus;
import com.paulocoimbra.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        State st1 = new State(null, "Minas Gerais");
        State st2 = new State(null, "São paulo");

        City c1 = new City(null, "Uberlandia", st1);
        City c2 = new City(null, "São Paulo", st2);
        City c3 = new City(null, "Campinas", st2);

        st1.getCities().addAll(Arrays.asList(c1));
        st2.getCities().addAll(Arrays.asList(c2, c3));

        stateRepository.saveAll(Arrays.asList(st1, st2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "1231231232", ClientType.PESSOAFISICA);
        cli1.getPhoneNumbers().addAll(Arrays.asList("12312323", "867588758"));

        Address a1 = new Address(null, "Rua flores", "300", "312312", cli1, c1);
        Address a2 = new Address(null, "Avenida matos", "105", "484854", cli1, c2);

        cli1.getAddresses().addAll(Arrays.asList(a1, a2));

        clientRepository.saveAll(Arrays.asList(cli1));
        addressRepository.saveAll(Arrays.asList(a1, a2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Order1 ord1 = new Order1(null, sdf.parse("30/09/2017 10:32"), cli1, a1);
        Order1 ord2 = new Order1(null, sdf.parse("10/10/2017 19:35"), cli1, a2);

        Payment payment1 = new PaymentWithCard(null, PaymentStatus.SETTLED, ord1, 6);
        ord1.setPayment(payment1);

        Payment payment2 = new PaymentWithBill(null, PaymentStatus.PENDING, ord2, sdf.parse("20/10/2017 00:00"), null);
        ord2.setPayment(payment2);

        cli1.getOrders().addAll(Arrays.asList(ord1, ord2));

        orderRepository.saveAll(Arrays.asList(ord1, ord2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));

        ItemOrder io1 = new ItemOrder(ord1, p1, 0.00, 1, 2000.00);
        ItemOrder io2 = new ItemOrder(ord1, p3, 0.00, 2, 80.00);
        ItemOrder io3 = new ItemOrder(ord2, p2, 100.00, 1, 800.00);

        ord1.getItems().addAll(Arrays.asList(io1, io2));
        ord2.getItems().addAll(Arrays.asList(io3));

        p1.getItems().addAll(Arrays.asList(io1));
        p2.getItems().addAll(Arrays.asList(io3));
        p3.getItems().addAll(Arrays.asList(io2));

        itemOrderRepository.saveAll(Arrays.asList(io1, io2, io3));

    }
}
