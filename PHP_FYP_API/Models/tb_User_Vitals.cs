//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace PHP_FYP_API.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class tb_User_Vitals
    {
        public int uvi_id { get; set; }
        public Nullable<int> u_id { get; set; }
        public string uvi_temperature { get; set; }
        public string uvi_pulserate { get; set; }
        public string uvi_respirationrate { get; set; }
        public string uvi_bloodpressure { get; set; }
        public string uvi_weight { get; set; }
        public string uvi_height { get; set; }
        public string lastUpdated { get; set; }
    }
}
