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
    
    public partial class tb_User_Medications
    {
        public int um_id { get; set; }
        public Nullable<int> u_id { get; set; }
        public Nullable<int> m_id { get; set; }
        public Nullable<int> um_dosage { get; set; }
        public string um_StartDate { get; set; }
        public string um_EndDate { get; set; }
        public string lastUpdated { get; set; }
    }
}