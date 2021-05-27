using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using System.Diagnostics;

namespace pubprojectassist
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();

        }

        private void button1_Click(object sender, EventArgs e)
        {
            try
            {
                StreamWriter write = new StreamWriter("webapp\\templates\\index.html");
                // combo box for sub id aligns up with the sub ids on the python flask web app, therefore, just writing the selected index works
                write.Write(subID.SelectedIndex + ",-," + price.Text + ",-," + date.Text + ",-," + udate.Text);
                write.Close();
                MessageBox.Show("success");
            }
            catch (IOException ex)
            {
                MessageBox.Show("io exception occurred");
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            date.Text = DateTime.Today.ToString("M/dd/yyyy") + " - " + DateTime.Today.AddDays(6).ToString("M/dd/yyyy");
            udate.Text = DateTime.Today.ToString("M/dd/yyyy");
        }

		private void button2_Click(object sender, EventArgs e)
		{
            Process.Start("webapp\\bash.bat");
		}
	}
}
