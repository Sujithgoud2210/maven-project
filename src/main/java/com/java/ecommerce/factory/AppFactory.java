package com.java.ecommerce.factory;

public class AppFactory {

    // =========================
    // CORE SINGLETON
    // =========================
    private static CsvParser csvParser;

    // =========================
    // AUTH FLOW
    // =========================

    private static AuthController authController;
    private static AuthService authService;
    private static AuthUI authUI;

    // =========================
    // CUSTOMER FLOW
    // =========================
    private static CustomerRepository customerRepository;
    private static CustomerService customerService;
    private static CustomerController customerController;
    private static CustomerUI customerUI;
    private static DashboardUI dashboardUI;


    // =========================
    // PRODUCT FLOW
    // =========================
    private static ProductRepository productRepository;
    private static ProductService productService;
    private static ProductController productController;
    private static ProductUI productUI;

    // =========================
    // CSV READER
    // =========================
    public static CsvParser getCsvParser() {
        if (csvParser == null) {
            csvParser = new CsvParser();
        }
        return csvParser;
    }

    // =========================
    // CUSTOMER DEPENDENCIES
    // =========================

    public static CustomerRepository getCustomerRepository() throws IOException {
        if (customerRepository == null) {
            customerRepository = new CustomerRepository(getCsvParser());
        }
        return customerRepository;
    }

    public static CustomerService getCustomerService() throws IOException {
        if (customerService == null) {
            customerService = new CustomerServiceImpl(getCustomerRepository());
        }
        return customerService;
    }

    public static CustomerController getCustomerController() throws IOException {
        if (customerController == null) {
            customerController = new CustomerController(getCustomerService());
        }
        return customerController;
    }

    // =========================
    // AUTH DEPENDENCIES
    // =========================

    public static AuthService getAuthService() throws IOException {
        if (authService == null) {
            authService = new AuthServiceImpl(getCustomerService());
        }
        return authService;
    }


    public static AuthController getAuthController() throws IOException {
        if (authController == null) {
            authController = new AuthController(getAuthService());
        }
        return authController;
    }

    // =========================
    // PRODUCT DEPENDENCIES
    // =========================

    public static ProductRepository getProductRepository() throws IOException {
        if (productRepository == null) {
            productRepository = new ProductRepository(getCsvParser());
        }
        return productRepository;
    }

    public static ProductService getProductService() throws IOException {
        if (productService == null) {
            productService = new ProductServiceImpl(getProductRepository());
        }
        return productService;
    }

    public static ProductController getProductController() throws IOException {
        if (productController == null) {
            productController = new ProductController(getProductService());
        }
        return productController;
    }

    public static AuthUI getAuthUI() throws IOException {
        if(authUI == null)
            authUI = new AuthUI(getAuthController());
        return authUI;
    }
    public static CustomerUI getCustomerUI(Customer customer) {
        if(customerUI == null)
            customerUI = new CustomerUI(customer);
        return customerUI;
    }
    public static DashboardUI getDashboardUI(Customer customer) {
        if(dashboardUI == null)
            dashboardUI = new DashboardUI(customer);
        return dashboardUI;
    }





}