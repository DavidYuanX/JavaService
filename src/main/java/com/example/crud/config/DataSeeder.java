package com.example.crud.config;

import com.example.crud.model.AuthUser;
import com.example.crud.model.Product;
import com.example.crud.repository.AuthUserRepository;
import com.example.crud.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    public DataSeeder(AuthUserRepository authUserRepository, PasswordEncoder passwordEncoder,
                      ProductRepository productRepository) {
        this.authUserRepository = authUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        if (authUserRepository.findByUsername("admin").isEmpty()) {
            AuthUser admin = new AuthUser("admin", passwordEncoder.encode("admin123"));
            authUserRepository.save(admin);
        }
        if (productRepository.count() == 0) {
            List<Product> samples = List.of(
                    product("极光机械键盘", "RGB 背光，青轴，全键无冲", "299.00", "https://images.unsplash.com/photo-1511467687858-23d96c32e4ae?w=400"),
                    product("无线降噪耳机", "主动降噪 30h 续航", "599.00", "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?w=400"),
                    product("便携充电宝", "20000mAh 双向快充", "129.00", "https://images.unsplash.com/photo-1609091839311-d5365f9ff1c5?w=400"),
                    product("智能手表", "心率血氧 50米防水", "899.00", "https://images.unsplash.com/photo-1523275335684-37898b6baf30?w=400"),
                    product("无线鼠标", "人体工学 静音按键", "89.00", "https://images.unsplash.com/photo-1527864550417-7fd91fc51a46?w=400")
            );
            productRepository.saveAll(samples);
        }
    }

    private static Product product(String name, String desc, String price, String imageUrl) {
        Product p = new Product();
        p.setName(name);
        p.setDescription(desc);
        p.setPrice(new BigDecimal(price));
        p.setImageUrl(imageUrl);
        return p;
    }
}
