package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Default;


import javax.inject.Inject;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogsCardsModel {
    @Inject
    private String sectionId;

    @Inject
    private String cardstitle;

    @Inject
    private String recentBlogsTitle;

    @Inject
    private List<BlogCardsBeanModel> carddetail;

    @Inject
    private int noOfCards;

    public String getSectionId() {
        return sectionId;
    }

    public String getCardstitle() {
        return cardstitle;
    }

    public List<BlogCardsBeanModel> getCarddetail() {
        sortBlogs(carddetail);
        return Collections.unmodifiableList(carddetail);
    }

    public String getRecentBlogsTitle() {
        return recentBlogsTitle;
    }

    public int getNoOfCards() {
        return noOfCards;
    }

    private void sortBlogs(List<BlogCardsBeanModel> eventList) {
        eventList.sort(new Comparator<BlogCardsBeanModel>() {
            @Override
            public int compare(BlogCardsBeanModel o1, BlogCardsBeanModel o2) {
                return o2.getBlogDate().compareTo(o1.getBlogDate());
            }
        });
    }
}
