package bean;

import lombok.Data;

/**
 * Author: kuzan
 * Date: 2018-01-14 17:07
 * Desc: ESNoteModel
 */
@Data
public class ESNoteModel {
    private String node_name;
    private String host;
    private String httpPort;
    private String tcpPort;
    private String httpEnabled;  // only yes or no
    private String node_master;
    private String node_data;
    private String unicast_hosts;
}
