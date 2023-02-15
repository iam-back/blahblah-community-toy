package data.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PageInfo {

    private int page;
    private int pageSize;
    private int recordSize;
    private String keyword;

    public PageInfo(){
        this.page = 1;
        this.pageSize = 10;
        this.recordSize = 20;
    }

    public int getOffset(){
        return (this.page - 1) * this.recordSize;
    }
}
