package apirest;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class CrewBean {

    @Inject
    private CrewMemberDAO crewMemberDao;

    public List<CrewMember> getCrewMembers() {
        return crewMemberDao.getCrewMembers();
    }

    public boolean addMember(CrewMember crewMember) {
        return crewMemberDao.addCrewMember(crewMember);
    }

    public void deleteMember(String name){

    }
}