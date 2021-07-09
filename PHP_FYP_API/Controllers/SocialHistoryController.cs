using PHP_FYP_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PHP_FYP_API.Controllers
{
    public class SocialHistoryController : ApiController
    {
        PHP_FYPEntities db = new PHP_FYPEntities();


        //  API to Get All User Social History     ->  C
        [HttpGet]
        public HttpResponseMessage GetUserSocialHistory(int id)
        {
            try
            {
                var ush = db.tb_User_Social_History.Where(sh => sh.u_id == id).ToList();
                if (ush.Count() > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, ush);
                }
                else
                    return Request.CreateResponse(HttpStatusCode.NotFound, "No Record");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        //  API to Add User Social Hisory     ->  C
        [HttpPost]
        public HttpResponseMessage AddingUserSocialHistory(tb_User_Social_History sh)
        {
            try
            {
                db.tb_User_Social_History.Add(sh);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, sh);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }
    }
}
