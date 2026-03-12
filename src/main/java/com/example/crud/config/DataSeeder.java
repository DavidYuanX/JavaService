package com.example.crud.config;

import com.example.crud.model.AuthUser;
import com.example.crud.model.Product;
import com.example.crud.repository.AuthUserRepository;
import com.example.crud.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AuthUserRepository authUserRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(AuthUserRepository authUserRepository,
                      ProductRepository productRepository,
                      PasswordEncoder passwordEncoder) {
        this.authUserRepository = authUserRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (authUserRepository.findByUsername("admin").isEmpty()) {
            AuthUser admin = new AuthUser("admin", passwordEncoder.encode("admin123"));
            authUserRepository.save(admin);
        }

        if (productRepository.count() == 0) {
            seedProducts();
        }
    }

    private void seedProducts() {
        List<Product> products = List.of(
            new Product("MacBook Pro 14寸",
                "Apple M3 Pro 芯片，18GB 统一内存，512GB 固态硬盘，Liquid Retina XDR 显示屏，深空黑色。",
                12999.0, 14999.0,
                "https://placehold.co/400x400/1a1a2e/eee?text=MacBook",
                "数码", 4.9, 5230),

            new Product("AirPods Pro 2",
                "主动降噪，自适应音频，个性化空间音频，MagSafe 充电盒，USB-C 接口。",
                1799.0, 1999.0,
                "https://placehold.co/400x400/16213e/eee?text=AirPods",
                "数码", 4.8, 12450),

            new Product("iPhone 16 Pro",
                "A18 Pro 芯片，钛金属设计，4800万像素摄像头系统，动作按钮，USB-C 接口，256GB。",
                8999.0, 9999.0,
                "https://placehold.co/400x400/2d3436/eee?text=iPhone",
                "数码", 4.9, 28900),

            new Product("iPad Air M2",
                "M2 芯片，10.9英寸 Liquid Retina 显示屏，支持 Apple Pencil 和妙控键盘。",
                4799.0, 5499.0,
                "https://placehold.co/400x400/16213e/eee?text=iPad",
                "数码", 4.8, 7800),

            new Product("Apple Watch Ultra 2",
                "49mm 钛金属表壳，精准双频GPS，水深仪，深度计，100m防水，续航36小时。",
                5999.0, 6499.0,
                "https://placehold.co/400x400/636e72/eee?text=Watch",
                "数码", 4.7, 3200),

            new Product("纯棉休闲T恤",
                "100%纯棉面料，舒适透气，简约百搭圆领短袖，多色可选。适合春夏日常穿搭。",
                89.0, 159.0,
                "https://placehold.co/400x400/0f3460/eee?text=T-Shirt",
                "服饰", 4.5, 32100),

            new Product("羊绒围巾",
                "100%山羊绒，柔软亲肤，经典纯色设计，冬季保暖必备，男女通用。",
                399.0, 699.0,
                "https://placehold.co/400x400/e94560/eee?text=Scarf",
                "服饰", 4.7, 5400),

            new Product("轻奢商务衬衫",
                "免烫工艺，修身剪裁，进口面料，商务休闲两相宜，白色/蓝色/灰色可选。",
                259.0, 499.0,
                "https://placehold.co/400x400/34495e/eee?text=Shirt",
                "服饰", 4.6, 8700),

            new Product("牛仔休闲裤",
                "弹力面料，直筒版型，经典水洗蓝，四季百搭，舒适不紧绷。",
                199.0, 359.0,
                "https://placehold.co/400x400/2c3e50/eee?text=Jeans",
                "服饰", 4.4, 15300),

            new Product("有机坚果礼盒",
                "精选6种坚果，每日坚果混合装，独立小包装，新鲜锁味，送礼佳品。30包/盒。",
                128.0, 198.0,
                "https://placehold.co/400x400/e94560/eee?text=Nuts",
                "食品", 4.7, 15600),

            new Product("龙井春茶礼盒",
                "明前特级龙井，手工采摘，清香甘甜，精美礼盒包装，250g装。产自杭州西湖。",
                268.0, 398.0,
                "https://placehold.co/400x400/16213e/eee?text=Tea",
                "食品", 4.8, 9100),

            new Product("进口黑巧克力",
                "85%可可含量，比利时原装进口，低糖配方，丝滑口感，100g*3盒装。",
                89.0, 129.0,
                "https://placehold.co/400x400/3d1c02/eee?text=Choco",
                "食品", 4.6, 21000),

            new Product("精酿啤酒组合装",
                "6种风味精酿，IPA/Stout/Lager/Wheat 各一瓶，330ml*6 礼盒装。",
                99.0, 158.0,
                "https://placehold.co/400x400/d4a017/eee?text=Beer",
                "食品", 4.5, 7800),

            new Product("北欧简约台灯",
                "三档调光，护眼LED光源，木质底座，适合书桌卧室床头使用。暖白光/冷白光可选。",
                159.0, 239.0,
                "https://placehold.co/400x400/1a1a2e/eee?text=Lamp",
                "家居", 4.4, 6700),

            new Product("智能保温杯",
                "LED温度显示，316不锈钢内胆，12小时长效保温，触控感应，500ml容量。",
                129.0, 199.0,
                "https://placehold.co/400x400/1a1a2e/eee?text=Cup",
                "家居", 4.5, 18200),

            new Product("记忆棉枕头",
                "慢回弹太空记忆棉，人体工学曲线设计，透气可拆洗外套，缓解颈椎疲劳。",
                168.0, 299.0,
                "https://placehold.co/400x400/8395a7/eee?text=Pillow",
                "家居", 4.7, 12400),

            new Product("香薰蜡烛礼盒",
                "天然大豆蜡，4种香型（白茶/薰衣草/柑橘/檀木），每只50g，燃烧时间约15h。",
                79.0, 139.0,
                "https://placehold.co/400x400/c0392b/eee?text=Candle",
                "家居", 4.3, 9600),

            new Product("运动休闲鞋",
                "轻便透气飞织鞋面，软弹缓震中底，防滑耐磨橡胶大底，适合日常通勤与运动。",
                299.0, 499.0,
                "https://placehold.co/400x400/533483/eee?text=Shoes",
                "运动", 4.6, 8900),

            new Product("瑜伽垫加厚防滑",
                "NBR高密度材质，15mm加厚，双面防滑纹理，附带绑带和背包，适合家庭健身。",
                79.0, 129.0,
                "https://placehold.co/400x400/0f3460/eee?text=Yoga",
                "运动", 4.3, 21000),

            new Product("跳绳计数器",
                "钢丝绳芯，铝合金手柄，内置电子计数器，可调节长度，适合有氧训练和拳击热身。",
                49.0, 89.0,
                "https://placehold.co/400x400/27ae60/eee?text=Rope",
                "运动", 4.4, 16500),

            new Product("哑铃套装",
                "包胶环保材质，5kg*2只，防滑手柄，家用健身增肌必备。",
                159.0, 249.0,
                "https://placehold.co/400x400/2c3e50/eee?text=Dumbbell",
                "运动", 4.5, 5600),

            new Product("编程入门经典套装",
                "包含《算法导论》《代码整洁之道》《设计模式》三本经典著作，程序员必读。",
                168.0, 258.0,
                "https://placehold.co/400x400/533483/eee?text=Books",
                "图书", 4.9, 4300),

            new Product("人类简史三部曲",
                "尤瓦尔·赫拉利代表作，《人类简史》《未来简史》《今日简史》精装合集。",
                128.0, 199.0,
                "https://placehold.co/400x400/8e44ad/eee?text=History",
                "图书", 4.8, 11200),

            new Product("小王子（精装插图版）",
                "圣埃克苏佩里经典之作，全新译本，精美水彩插图，中英双语对照，精装珍藏版。",
                39.0, 68.0,
                "https://placehold.co/400x400/3498db/eee?text=Prince",
                "图书", 4.9, 35000)
        );

        productRepository.saveAll(products);
    }
}
