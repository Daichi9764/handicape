package apphandicaped.Database;

import apphandicaped.Database.Request.RequestStatus;


public class Request {
    public enum RequestStatus {
        PENDING, INPROGRESS, COMPLETED, REJECTED
    }

    public RequestStatus Rstatus;

    public Request(RequestStatus Rstatus) {
        this.Rstatus = Rstatus;
    }


    public void setRStatus(RequestStatus rs){
        this.Rstatus = rs;
    }

    public RequestStatus getRStatus(){
        return this.Rstatus;
    }

}