package example.models;

import com.geniussports.soy.annotations.Soy;

@Soy
@Soy.Template("models.StatisticsTemplate")
public class Statistics {
    private final Integer downloads, pageViews, uniqueVisitors;

    public Statistics(Integer downloads, Integer pageViews, Integer uniqueVisitors) {
        this.downloads = downloads;
        this.pageViews = pageViews;
        this.uniqueVisitors = uniqueVisitors;
    }

    @Soy.Method("Downloads")
    public Integer getDownloads() {
        return downloads;
    }


    @Soy.Method("PageViews")
    public Integer getPageViews() {
        return pageViews;
    }


    @Soy.Method("UniqueVisitors")
    public Integer getUniqueVisitors() {
        return uniqueVisitors;
    }
}
