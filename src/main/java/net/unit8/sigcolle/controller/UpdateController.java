package net.unit8.sigcolle.controller;

import enkan.collection.Multimap;
import enkan.component.doma2.DomaProvider;
import enkan.data.HttpResponse;
import enkan.data.Session;
import kotowari.component.TemplateEngine;
import net.unit8.sigcolle.auth.LoginUserPrincipal;
import net.unit8.sigcolle.dao.CampaignDao;
import net.unit8.sigcolle.dao.UserDao;
import net.unit8.sigcolle.form.UpdateForm;
import net.unit8.sigcolle.model.Campaign;
import net.unit8.sigcolle.model.User;

import javax.inject.Inject;

import static enkan.util.HttpResponseUtils.RedirectStatusCode.SEE_OTHER;
import static enkan.util.HttpResponseUtils.redirect;

/**
 * Created by liu on 2017/03/11.
 */
public class UpdateController {
    @Inject
    private TemplateEngine templateEngine;

    @Inject
    private DomaProvider domaProvider;

    /**
     * ユーザーのデーターを得る
     */
/*    public HttpResponse listCampaigns(Session session) {
        CampaignDao campaignDao = domaProvider.getDao(CampaignDao.class);
        LoginUserPrincipal principal = (LoginUserPrincipal) session.get("principal");
        return templateEngine.render("user/list", "campaigns", campaignDao.selectByUserId(principal.getUserId()));
        //throw new UnsupportedOperationException("実装してください !!");
    }*/
    public HttpResponse listUsers(Session session) {
        UserDao userDao = domaProvider.getDao(UserDao.class);
        LoginUserPrincipal principal = (LoginUserPrincipal) session.get("principal");
        return templateEngine.render("user/update", "users", userDao.selectByUserId(principal.getUserId()));
    }

    public HttpResponse updateUser(UpdateForm form, Session session) {
        UserDao userDao = domaProvider.getDao(UserDao.class);

        LoginController logincon = new LoginController();

        LoginUserPrincipal principal = (LoginUserPrincipal) session.get("principal");

        User user = new User();
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setEmail(form.getEmail());
        user.setPass(form.getPass());

        user.setUserId(principal.getUserId());

        userDao.update(user);



        // Session session = new Session();
        //     User loginUser = userDao.selectByEmail(form.getEmail());
//        session.put(
//                "principal",
//                new LoginUserPrincipal(loginUser.getUserId(), loginUser.getLastName() + " " + loginUser.getFirstName())
//        );



        HttpResponse response = logincon.logout(session);
        //response.setSession(session);
        return response;
    }

}
