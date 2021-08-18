package core.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardResponse {

    public String name;
    public String desc;
    //public String[] descData;
    public boolean closed;
    public String dateClosed;
    public String idOrganisation;
    public String idEnterprise;
    public String limits;
    public String pinned;
    public String shortLink;
    public String[] powerUps;
    public String dateLastActivity;
    public String[] idTags;
    public String datePluginDisable;
    public String creationMethod;
    public String ixUpdate;
    public String enterpriseOwned;
    public String idBoardSource;
    public String idMemberCreator;
    public String id;
    public boolean started;
    public String url;
    public String shortUrl;
}
