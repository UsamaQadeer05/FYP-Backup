using PHP_FYP_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PHP_FYP_API.Controllers
{
    public class SymptomsController : ApiController
    {
        PHP_FYPEntities db = new PHP_FYPEntities();


        //  API to Get All Symptoms     ->  C
        [HttpGet]
        public HttpResponseMessage Symptoms()
        {
            try
            {
                var symptoms = db.tb_Symptoms.OrderBy(d => d.s_id).ToList();
                if (symptoms.Count() > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, symptoms);
                }
                else
                    return Request.CreateResponse(HttpStatusCode.NotFound, "No Record");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

        //  API to Add User Symptoms     ->  C
        [HttpPost]
        public HttpResponseMessage AddingUserSymptoms(tb_User_Symptoms uds)
        {
            try
            {
                db.tb_User_Symptoms.Add(uds);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, uds);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        //  API to Get User Disease Symptoms
        [HttpGet]
        public HttpResponseMessage GetUserSymptoms(int id)
        {
            try
            {
                var user_disease_symptoms = db.tb_User_Symptoms.Where(uds => uds.u_id == id).ToList();
                if (user_disease_symptoms.Count() > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, user_disease_symptoms);
                }
                else
                    return Request.CreateResponse(HttpStatusCode.NotFound, "No Record");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

    }
}
