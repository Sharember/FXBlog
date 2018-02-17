package cn.fanhub.fxblogui.model;

import cn.fanhub.fxblogui.entity.Article;
import cn.fanhub.fxblogui.entity.Tag;
import lombok.Data;
import lombok.ToString;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@ToString
public class AllCardInfoVO {

    private List<Card> cards;

    public AllCardInfoVO() {
        this.cards = new ArrayList<>();
    }

    public AllCardInfoVO convertVisitTop(List<Article> articles) {
        cards.add(new Card(
                "热门文章",
                 articles
                        .stream()
                        .map(article -> article.getName() + " (" + article.getVisitNum() + ")")
                        .collect(Collectors.toList()),
                "article"));
        return this;
    }

    public AllCardInfoVO convertCreateTop(List<Article> articles) {
        cards.add(new Card(
                "最新文章",
                 articles
                         .stream()
                         .map(article -> article.getName() + " (" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(article.getCreateTime()) + ")")
                         .collect(Collectors.toList()),
                "article"));
        return this;
    }

    public AllCardInfoVO convertTags(List<Tag> tags) {
        cards.add(new Card(
                "标签云",
                 tags
                    .stream()
                    .map(tag -> tag.getName() + "-" + tag.getArticleNum())
                    .collect(Collectors.toList()),
                "tags"));
        return this;
    }

    private class Card {
        private String title;
        private String type;
        private List<String> content;

        Card(String title, List<String> content, String type) {
            this.title = title;
            this.content = content;
            this.type = type;
        }


        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

}