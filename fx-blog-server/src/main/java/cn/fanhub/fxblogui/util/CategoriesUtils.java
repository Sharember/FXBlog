package cn.fanhub.fxblogui.util;

import cn.fanhub.fxblogui.entity.Categories;

import java.util.List;

public class CategoriesUtils {

    public static Categories getDeepCategories(Categories categories, List<String> names) {
        for (String name : names) {
            for (Categories sub : categories.getChildren()) {
                if (name.equalsIgnoreCase(sub.getName())) {
                    categories = sub;
                    break;
                }
            }
        }
        return categories;
    }

    public static void insert(Categories from, Categories to) {

        while (!from.getChildren().isEmpty()) {
            boolean flag = true;
            from = from.getChildren().get(0);
            for (Categories toSub : to.getChildren()) {
                if (from.getName().equalsIgnoreCase(toSub.getName())) {
                    to = toSub;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                to.getChildren().add(from);
            }

        }
    }
}