using PHP_FYP_API.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PHP_FYP_API.Controllers
{
    public class MedicationsController : ApiController
    {
        PHP_FYPEntities db = new PHP_FYPEntities();


        //  Get all Medicines
        [HttpGet]
        public HttpResponseMessage Medicines()
        {
            try
            {
                var me = db.tb_Medications.OrderBy(m => m.m_id).ToList();
                if (me.Count() > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, me);
                }
                else
                    return Request.CreateResponse(HttpStatusCode.NotFound, "No Record");
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }


        //  Adding User Medicines
        [HttpPost]
        public HttpResponseMessage AddingUserMedicines(tb_User_Medications um)
        {
            try
            {
                db.tb_User_Medications.Add(um);
                db.SaveChanges();
                return Request.CreateResponse(HttpStatusCode.OK, um);
            }
            catch (Exception ex)
            {
                return Request.CreateResponse(HttpStatusCode.InternalServerError, ex.Message);
            }
        }

   
        //  Get User Medicines
        [HttpGet]
        public HttpResponseMessage GetUserMedicines(int id)
        {
            try
            {
                var um = db.tb_User_Medications.Where(m => m.u_id == id).ToList();
                if (um.Count() > 0)
                {
                    return Request.CreateResponse(HttpStatusCode.OK, um);
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
