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

    /**
     *
     * to -> {a -> {c -> {e, d}}, b}
     *
     * @param from
     * @param to
     */
    public static void insert(Categories from, Categories to) {
        to.setArticleNum(to.getArticleNum() + 1);
        while (!from.getChildren().isEmpty()) {
            boolean flag = true;
            from = from.getChildren().get(0);
            for (int i = 0; i < to.getChildren().size(); i++) {
                Categories toSub = to.getChildren().get(i);

                if (from.getName().equalsIgnoreCase(toSub.getName())) {
                    to = toSub;
                    to.setArticleNum(to.getArticleNum() + 1);
                    flag = false;
                    break;
                }
            }
            // from a -> c
            if (!flag && from.getChildren().isEmpty()) {
                to.getArticles().add(from.getArticles().get(0));
                return;
            }

            // from : a -> c -> f
            if (flag) {
                to.setArticleNum(to.getArticleNum() + 1);
                to.getChildren().add(from);
                return;
            }

        }

        // from: a
        to.getArticles().add(from.getArticles().get(0));
    }

    public static Categories convert(List<String> list, long id) {
        Categories categories = new Categories();
        if (list.size() == 0) {
            categories.setName("default");
            categories.setArticleNum(1);
            categories.getArticles().add(id);
            return categories;
        }

        Categories temp = categories;
        categories.setArticleNum(1);
        categories.setName(list.get(0));
        for (int i = 1; i < list.size(); i++) {
            Categories sub = new Categories();
            sub.setName(list.get(i));
            sub.setArticleNum(1);
            temp.getChildren().add(sub);
            temp = sub;
        }
        temp.getArticles().add(id);
        return categories;
    }
}