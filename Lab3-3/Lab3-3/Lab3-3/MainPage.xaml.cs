using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;

namespace Lab3_3
{
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }

        void Button_Pressed(System.Object sender, System.EventArgs e)
        {
            var currentDate = DateTime.Now;

            labelDate.Text = currentDate.ToShortDateString() + " " + currentDate.ToShortTimeString();
        }
    }
}

