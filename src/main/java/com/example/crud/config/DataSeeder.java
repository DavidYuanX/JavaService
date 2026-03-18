package com.example.crud.config;

import com.example.crud.model.User;
import com.example.crud.model.Product;
import com.example.crud.model.Banner;
import com.example.crud.repository.UserRepository;
import com.example.crud.repository.ProductRepository;
import com.example.crud.repository.BannerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final BannerRepository bannerRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository,
                      ProductRepository productRepository,
                      BannerRepository bannerRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.bannerRepository = bannerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User("admin", passwordEncoder.encode("admin123"), User.Role.ADMIN);
            userRepository.save(admin);
        }

        // 初始化Banner数据
        if (bannerRepository.count() == 0) {
            seedBanners();
        }

        // 如果商品数量少于预期，清空并重新初始化
        if (productRepository.count() < 2000) {
            productRepository.deleteAll();
            seedProducts();
        }
    }

    private void seedBanners() {
        List<Banner> banners = List.of(
            new Banner("春季大促 全场低至5折", "限时特惠，错过等一年", null, "#1a1a2e"),
            new Banner("数码新品 限时特惠", "最新数码产品首发优惠", null, "#16213e"),
            new Banner("每日坚果 买二送一", "健康零食，天天特价", null, "#0f3460")
        );
        bannerRepository.saveAll(banners);
    }

    private void seedProducts() {
        List<Product> products = new java.util.ArrayList<>(List.of(
            // 数码产品
            new Product("MacBook Pro 14寸",
                "Apple M3 Pro 芯片，18GB 统一内存，512GB 固态硬盘，Liquid Retina XDR 显示屏，深空黑色。",
                12999.0, 14999.0,
                "https://picsum.photos/seed/macbook/400/400",
                "数码", 4.9, 5230),

            new Product("AirPods Pro 2",
                "主动降噪，自适应音频，个性化空间音频，MagSafe 充电盒，USB-C 接口。",
                1799.0, 1999.0,
                "https://picsum.photos/seed/airpods/400/400",
                "数码", 4.8, 12450),

            new Product("iPhone 16 Pro",
                "A18 Pro 芯片，钛金属设计，4800万像素摄像头系统，动作按钮，USB-C 接口，256GB。",
                8999.0, 9999.0,
                "https://picsum.photos/seed/iphone/400/400",
                "数码", 4.9, 28900),

            new Product("iPad Air M2",
                "M2 芯片，10.9英寸 Liquid Retina 显示屏，支持 Apple Pencil 和妙控键盘。",
                4799.0, 5499.0,
                "https://picsum.photos/seed/ipad/400/400",
                "数码", 4.8, 7800),

            new Product("Apple Watch Ultra 2",
                "49mm 钛金属表壳，精准双频GPS，水深仪，深度计，100m防水，续航36小时。",
                5999.0, 6499.0,
                "https://picsum.photos/seed/watch/400/400",
                "数码", 4.7, 3200),

            new Product("Sony WH-1000XM5 耳机",
                "旗舰降噪头戴式耳机，30小时续航，多设备连接，舒适佩戴设计。",
                2499.0, 2999.0,
                "https://picsum.photos/seed/sony/400/400",
                "数码", 4.9, 8900),

            new Product("Samsung Galaxy S24 Ultra",
                "骁龙8 Gen3，2亿像素，S Pen，钛金属边框，12GB+512GB。",
                9999.0, 11999.0,
                "https://picsum.photos/seed/galaxy/400/400",
                "数码", 4.8, 12500),

            new Product("Nintendo Switch OLED",
                "7英寸OLED屏幕，64GB存储，增强音频，支持掌机/主机双模式。",
                2499.0, 2999.0,
                "https://picsum.photos/seed/switch/400/400",
                "数码", 4.7, 18700),

            new Product("PlayStation 5",
                "次世代游戏主机，4K光追，超高速SSD，DualSense手柄。",
                3899.0, 4499.0,
                "https://picsum.photos/seed/ps5/400/400",
                "数码", 4.9, 22100),

            new Product("DJI Mini 4 Pro 无人机",
                "4K/60fps视频，34分钟续航，全向避障，249g轻量化设计。",
                6188.0, 6888.0,
                "https://picsum.photos/seed/dji/400/400",
                "数码", 4.8, 5600),

            // 服饰
            new Product("纯棉休闲T恤",
                "100%纯棉面料，舒适透气，简约百搭圆领短袖，多色可选。",
                89.0, 159.0,
                "https://picsum.photos/seed/tshirt/400/400",
                "服饰", 4.5, 32100),

            new Product("羊绒围巾",
                "100%山羊绒，柔软亲肤，经典纯色设计，冬季保暖必备。",
                399.0, 699.0,
                "https://picsum.photos/seed/scarf/400/400",
                "服饰", 4.7, 5400),

            new Product("轻奢商务衬衫",
                "免烫工艺，修身剪裁，进口面料，商务休闲两相宜。",
                259.0, 499.0,
                "https://picsum.photos/seed/shirt/400/400",
                "服饰", 4.6, 8700),

            new Product("牛仔休闲裤",
                "弹力面料，直筒版型，经典水洗蓝，四季百搭。",
                199.0, 359.0,
                "https://picsum.photos/seed/jeans/400/400",
                "服饰", 4.4, 15300),

            new Product("羊毛混纺大衣",
                "50%羊毛，经典双排扣设计，保暖有型，秋冬必备。",
                599.0, 999.0,
                "https://picsum.photos/seed/coat/400/400",
                "服饰", 4.6, 4200),

            new Product("运动卫衣",
                "加绒保暖，宽松版型，连帽设计，多色可选。",
                159.0, 259.0,
                "https://picsum.photos/seed/hoodie/400/400",
                "服饰", 4.5, 18900),

            new Product("真丝睡衣套装",
                "100%桑蚕丝，亲肤透气，高档礼盒包装，送长辈首选。",
                459.0, 799.0,
                "https://picsum.photos/seed/pajamas/400/400",
                "服饰", 4.8, 6700),

            new Product("休闲帆布鞋",
                "经典低帮设计，透气帆布，橡胶防滑底，百搭款式。",
                129.0, 199.0,
                "https://picsum.photos/seed/canvas/400/400",
                "服饰", 4.3, 25600),

            // 食品
            new Product("有机坚果礼盒",
                "精选6种坚果，每日坚果混合装，独立小包装，30包/盒。",
                128.0, 198.0,
                "https://picsum.photos/seed/nuts/400/400",
                "食品", 4.7, 15600),

            new Product("龙井春茶礼盒",
                "明前特级龙井，手工采摘，清香甘甜，精美礼盒，250g装。",
                268.0, 398.0,
                "https://picsum.photos/seed/tea/400/400",
                "食品", 4.8, 9100),

            new Product("进口黑巧克力",
                "85%可可含量，比利时原装进口，低糖配方，100g*3盒装。",
                89.0, 129.0,
                "https://picsum.photos/seed/choco/400/400",
                "食品", 4.6, 21000),

            new Product("精酿啤酒组合装",
                "6种风味精酿，IPA/Stout/Lager/Wheat，330ml*6礼盒装。",
                99.0, 158.0,
                "https://picsum.photos/seed/beer/400/400",
                "食品", 4.5, 7800),

            new Product("澳洲牛排礼盒",
                "谷饲180天，原切牛排，5片装，每片200g，附带黑胡椒酱。",
                299.0, 459.0,
                "https://picsum.photos/seed/steak/400/400",
                "食品", 4.9, 8900),

            new Product("日本抹茶粉",
                "京都宇治产地，石臼研磨，色泽翠绿，茶香浓郁，100g罐装。",
                168.0, 268.0,
                "https://picsum.photos/seed/matcha/400/400",
                "食品", 4.7, 12300),

            new Product("进口橄榄油",
                "西班牙特级初榨，酸度≤0.4%，500ml*2瓶装，健康烹饪首选。",
                139.0, 199.0,
                "https://picsum.photos/seed/olive/400/400",
                "食品", 4.6, 17600),

            // 家居
            new Product("北欧简约台灯",
                "三档调光，护眼LED光源，木质底座，暖白光/冷白光可选。",
                159.0, 239.0,
                "https://picsum.photos/seed/lamp/400/400",
                "家居", 4.4, 6700),

            new Product("智能保温杯",
                "LED温度显示，316不锈钢内胆，12小时长效保温，500ml。",
                129.0, 199.0,
                "https://picsum.photos/seed/cup/400/400",
                "家居", 4.5, 18200),

            new Product("记忆棉枕头",
                "慢回弹太空记忆棉，人体工学曲线设计，透气可拆洗外套。",
                168.0, 299.0,
                "https://picsum.photos/seed/pillow/400/400",
                "家居", 4.7, 12400),

            new Product("香薰蜡烛礼盒",
                "天然大豆蜡，4种香型，每只50g，燃烧时间约15h。",
                79.0, 139.0,
                "https://picsum.photos/seed/candle/400/400",
                "家居", 4.3, 9600),

            new Product("全自动咖啡机",
                "意式浓缩，一键奶泡，15bar高压，可拆卸水箱，适合家用。",
                1299.0, 1899.0,
                "https://picsum.photos/seed/coffee/400/400",
                "家居", 4.6, 4500),

            new Product("扫地机器人",
                "激光导航，4000Pa吸力，自动回充，APP智能控制，支持划区清扫。",
                1999.0, 2699.0,
                "https://picsum.photos/seed/robot/400/400",
                "家居", 4.7, 7800),

            new Product("空气净化器",
                "HEPA H13滤网，去除99.97%颗粒物，PM2.5实时显示，适用30㎡。",
                899.0, 1299.0,
                "https://picsum.photos/seed/purifier/400/400",
                "家居", 4.5, 11200),

            new Product("智能门锁",
                "指纹/密码/卡片/钥匙四合一，半导体指纹识别，防撬报警。",
                1599.0, 2199.0,
                "https://picsum.photos/seed/lock/400/400",
                "家居", 4.8, 5600),

            // 运动
            new Product("运动休闲鞋",
                "轻便透气飞织鞋面，软弹缓震中底，防滑耐磨橡胶大底。",
                299.0, 499.0,
                "https://picsum.photos/seed/shoes/400/400",
                "运动", 4.6, 8900),

            new Product("瑜伽垫加厚防滑",
                "NBR高密度材质，15mm加厚，双面防滑纹理，附带绑带和背包。",
                79.0, 129.0,
                "https://picsum.photos/seed/yoga/400/400",
                "运动", 4.3, 21000),

            new Product("跳绳计数器",
                "钢丝绳芯，铝合金手柄，内置电子计数器，可调节长度。",
                49.0, 89.0,
                "https://picsum.photos/seed/rope/400/400",
                "运动", 4.4, 16500),

            new Product("哑铃套装",
                "包胶环保材质，5kg*2只，防滑手柄，家用健身必备。",
                159.0, 249.0,
                "https://picsum.photos/seed/dumbbell/400/400",
                "运动", 4.5, 5600),

            new Product("跑步机家用折叠",
                "静音电机，LED显示，坡度可调，心率监测，可折叠收纳。",
                1999.0, 2999.0,
                "https://picsum.photos/seed/treadmill/400/400",
                "运动", 4.4, 3200),

            new Product("动感单车",
                "静音皮带传动，飞轮10kg，可调节座椅，手机支架，适合居家有氧。",
                899.0, 1399.0,
                "https://picsum.photos/seed/spinbike/400/400",
                "运动", 4.5, 4800),

            new Product("筋膜枪",
                "深筋膜放松，8档力度，4个按摩头，续航6小时，运动恢复必备。",
                259.0, 399.0,
                "https://picsum.photos/seed/massage/400/400",
                "运动", 4.6, 13400),

            new Product("泳镜防雾防水",
                "高清防雾镜片，硅胶密封，UV防护，适合游泳爱好者。",
                69.0, 119.0,
                "https://picsum.photos/seed/goggles/400/400",
                "运动", 4.4, 9200),

            // 图书
            new Product("编程入门经典套装",
                "包含《算法导论》《代码整洁之道》《设计模式》三本经典著作。",
                168.0, 258.0,
                "https://picsum.photos/seed/books/400/400",
                "图书", 4.9, 4300),

            new Product("人类简史三部曲",
                "尤瓦尔·赫拉利代表作，《人类简史》《未来简史》《今日简史》精装合集。",
                128.0, 199.0,
                "https://picsum.photos/seed/history/400/400",
                "图书", 4.8, 11200),

            new Product("小王子（精装插图版）",
                "圣埃克苏佩里经典之作，全新译本，精美水彩插图，中英双语对照。",
                39.0, 68.0,
                "https://picsum.photos/seed/prince/400/400",
                "图书", 4.9, 35000),

            new Product("思考快与慢",
                "诺贝尔经济学奖得主丹尼尔·卡尼曼代表作，认知心理学经典。",
                59.0, 89.0,
                "https://picsum.photos/seed/think/400/400",
                "图书", 4.7, 8900),

            new Product("活着（余华作品）",
                "余华代表作，讲述福贵的一生，感动千万读者的经典文学。",
                35.0, 58.0,
                "https://picsum.photos/seed/alive/400/400",
                "图书", 4.9, 28500),

            new Product("三体全集",
                "刘慈欣科幻巨著，雨果奖获奖作品，中国科幻里程碑。",
                89.0, 139.0,
                "https://picsum.photos/seed/threebody/400/400",
                "图书", 4.9, 45600),

            new Product("富爸爸穷爸爸",
                "财商教育经典，改变金钱观念，适合投资入门者。",
                49.0, 79.0,
                "https://picsum.photos/seed/richdad/400/400",
                "图书", 4.6, 17800),

            new Product("百年孤独",
                "马尔克斯代表作，魔幻现实主义文学巅峰之作。",
                55.0, 89.0,
                "https://picsum.photos/seed/solitude/400/400",
                "图书", 4.8, 15600)
        ));

        // 生成更多测试数据
        String[] categories = {"数码", "服饰", "食品", "家居", "运动", "图书"};
        String[] prefixes = {"新款", "精选", "优选", "特惠", "热销", "限量"};
        String[] suffixes = {"升级版", "2024款", "经典款", "旗舰版", "Pro版", "青春版"};

        java.util.Random random = new java.util.Random();
        for (int i = 1; i <= 1948; i++) {
            String category = categories[i % categories.length];
            String prefix = prefixes[i % prefixes.length];
            String suffix = suffixes[i % suffixes.length];
            String name = prefix + category + "商品" + i + "号 " + suffix;
            double price = 50 + random.nextDouble() * 500;
            price = Math.round(price * 10) / 10.0;
            double originalPrice = price * (1.1 + random.nextDouble() * 0.4);
            originalPrice = Math.round(originalPrice * 10) / 10.0;
            double rating = 4.0 + random.nextDouble() * 1.0;
            rating = Math.round(rating * 10) / 10.0;
            int sales = 100 + random.nextInt(50000);

            products.add(new Product(
                name,
                "这是一款" + category + "类别的测试商品，用于性能测试。商品编号：" + i + "，具有良好的品质和实惠的价格。",
                price, originalPrice,
                "https://picsum.photos/seed/product" + i + "/400/400",
                category, rating, sales
            ));
        }

        productRepository.saveAll(products);
    }
}
