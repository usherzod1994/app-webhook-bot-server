package com.example.appwebhookbotserver.bot;

public interface BotState {

    String MAIN_MENU = "main_menu";

    String CATEGORY_MENU_STATE = "category_menu_state";

    String CHILD_MENU_BY_CATEGORY_STATE = "child_category_menu_state";

    String PARENT_PRODUCT_STATE = "parent_product_state";

    String PRINT_PRODUCTS_STATE = "print_products_state";

    String NUMBER_PRODUCTS_STATE = "number_products_state";

    String TRASH_STATE = "trash_state";
}
