package teste.servicos;

import java.util.logging.Logger;

import org.json.JSONObject;
import teste.domain.ComponentImpl;
import teste.domain.Section;
import teste.domain.dao.DaoFactory;
import teste.servicepack.security.logic.*;
import teste.servicepack.security.logic.Permission.HasRole;
import teste.servicepack.security.logic.Permission.IsAuthenticated;
import teste.servicepack.security.logic.Permission.Transaction;
import teste.utils.HibernateUtils;

public class ComponentService {

    private static final Logger logger = Logger.getLogger(String.valueOf(ComponentService.class));


    //addComponent
    @IsAuthenticated
    @HasRole(role = "Admin")
    @Transaction
    public JSONObject addComponent(JSONObject Component){
        logger.info("Component");

        long idSection = Component.getLong("idSection");
        ComponentImpl Comp = ComponentImpl.fromJson(Component);
        Section section = DaoFactory.createSectionDao().load(idSection);

        if (Comp.getId() > 0){
            ComponentImpl CompPersistence = (ComponentImpl) DaoFactory.createComponentDao().get(Comp.getId());

            CompPersistence.setId(Comp.getId());
            CompPersistence.setSection(Comp.getSection());
            CompPersistence.setImg(Comp.getImg());
            CompPersistence.setText(Comp.getText());

            JSONObject jsonObject = new JSONObject(CompPersistence.toJson());

            return jsonObject;
        }else {
            section.getComponents().add(Comp);
            HibernateUtils.getCurrentSession().save(Comp);
        }
        return new JSONObject(Comp.toJson());
    }



}
