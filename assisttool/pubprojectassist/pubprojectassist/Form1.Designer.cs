namespace pubprojectassist
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
			this.subID = new System.Windows.Forms.ComboBox();
			this.price = new System.Windows.Forms.TextBox();
			this.date = new System.Windows.Forms.TextBox();
			this.udate = new System.Windows.Forms.TextBox();
			this.label1 = new System.Windows.Forms.Label();
			this.label2 = new System.Windows.Forms.Label();
			this.label3 = new System.Windows.Forms.Label();
			this.label4 = new System.Windows.Forms.Label();
			this.button1 = new System.Windows.Forms.Button();
			this.button2 = new System.Windows.Forms.Button();
			this.SuspendLayout();
			// 
			// subID
			// 
			this.subID.FormattingEnabled = true;
			this.subID.Items.AddRange(new object[] {
            "Chicken Tender",
            "Boar\'s Head Turkey",
            "Boar\'s Head Italian",
            "Boar\'s Head Ultimate",
            "Pub. Ultimate",
            "Boar\'s Head Ham",
            "Boar\'s Head Roast Beef",
            "Boar\'s Head Jerk Turkey & Gouda",
            "Chicken Cordon Bleu",
            "Boar\'s Head Maple Honey Turkey",
            "Boar\'s Head Philly Cheese",
            "Boar\'s Head Ham and Turkey",
            "Boar\'s Head Havana Bold",
            "Boar\'s Head Deluxe",
            "Boar\'s Head American",
            "Boar\'s Head EverRoast",
            "Boar\'s Head BLT",
            "Pub. Turkey",
            "Pub. Italian",
            "Pub. Ham",
            "Pub. Veggie",
            "Pub. Tuna Salad",
            "Pub. Roast Beef",
            "Pub. Philly Cheese",
            "Pub. Homestyle Beef Meatball",
            "Pub. Ham & Turkey",
            "Pub. Chicken Salad",
            "Pub. Mojo Pork",
            "Pub. Egg Salad",
            "Pub. Cuban",
            "Pub. Turkey Cranberry Holiday Sub"});
			this.subID.Location = new System.Drawing.Point(82, 31);
			this.subID.Name = "subID";
			this.subID.Size = new System.Drawing.Size(121, 21);
			this.subID.TabIndex = 0;
			// 
			// price
			// 
			this.price.Location = new System.Drawing.Point(94, 69);
			this.price.Name = "price";
			this.price.Size = new System.Drawing.Size(100, 20);
			this.price.TabIndex = 1;
			// 
			// date
			// 
			this.date.Location = new System.Drawing.Point(94, 109);
			this.date.Name = "date";
			this.date.Size = new System.Drawing.Size(100, 20);
			this.date.TabIndex = 2;
			// 
			// udate
			// 
			this.udate.Location = new System.Drawing.Point(94, 147);
			this.udate.Name = "udate";
			this.udate.Size = new System.Drawing.Size(100, 20);
			this.udate.TabIndex = 3;
			// 
			// label1
			// 
			this.label1.AutoSize = true;
			this.label1.Location = new System.Drawing.Point(40, 34);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(21, 13);
			this.label1.TabIndex = 4;
			this.label1.Text = "ID:";
			// 
			// label2
			// 
			this.label2.AutoSize = true;
			this.label2.Location = new System.Drawing.Point(24, 72);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(37, 13);
			this.label2.TabIndex = 5;
			this.label2.Text = "Price: ";
			// 
			// label3
			// 
			this.label3.AutoSize = true;
			this.label3.Location = new System.Drawing.Point(24, 109);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(33, 13);
			this.label3.TabIndex = 6;
			this.label3.Text = "Date:";
			// 
			// label4
			// 
			this.label4.AutoSize = true;
			this.label4.Location = new System.Drawing.Point(11, 150);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(77, 13);
			this.label4.TabIndex = 7;
			this.label4.Text = "Updated Date:";
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(53, 173);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(75, 23);
			this.button1.TabIndex = 8;
			this.button1.Text = "Write Info";
			this.button1.UseVisualStyleBackColor = true;
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// button2
			// 
			this.button2.Location = new System.Drawing.Point(161, 173);
			this.button2.Name = "button2";
			this.button2.Size = new System.Drawing.Size(75, 23);
			this.button2.TabIndex = 9;
			this.button2.Text = "Commit";
			this.button2.UseVisualStyleBackColor = true;
			this.button2.Click += new System.EventHandler(this.button2_Click);
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(301, 224);
			this.Controls.Add(this.button2);
			this.Controls.Add(this.button1);
			this.Controls.Add(this.label4);
			this.Controls.Add(this.label3);
			this.Controls.Add(this.label2);
			this.Controls.Add(this.label1);
			this.Controls.Add(this.udate);
			this.Controls.Add(this.date);
			this.Controls.Add(this.price);
			this.Controls.Add(this.subID);
			this.Name = "Form1";
			this.Text = "Form1";
			this.Load += new System.EventHandler(this.Form1_Load);
			this.ResumeLayout(false);
			this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox subID;
        private System.Windows.Forms.TextBox price;
        private System.Windows.Forms.TextBox date;
        private System.Windows.Forms.TextBox udate;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button button1;
		private System.Windows.Forms.Button button2;
	}
}

