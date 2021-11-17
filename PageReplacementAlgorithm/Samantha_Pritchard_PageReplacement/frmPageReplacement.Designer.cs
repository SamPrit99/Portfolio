
namespace Samantha_Pritchard_PageReplacement
{
    partial class frmPageReplacement
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.txtFrameNum = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.rdoRandom = new System.Windows.Forms.RadioButton();
            this.rdoEnter = new System.Windows.Forms.RadioButton();
            this.txtReferenceString = new System.Windows.Forms.TextBox();
            this.btnStart = new System.Windows.Forms.Button();
            this.lstResult = new System.Windows.Forms.ListBox();
            this.label2 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // txtFrameNum
            // 
            this.txtFrameNum.Location = new System.Drawing.Point(364, 165);
            this.txtFrameNum.Name = "txtFrameNum";
            this.txtFrameNum.Size = new System.Drawing.Size(104, 23);
            this.txtFrameNum.TabIndex = 0;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(38, 165);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(285, 15);
            this.label1.TabIndex = 1;
            this.label1.Text = "Please enter the number of frames (between 1 and 7)";
            // 
            // rdoRandom
            // 
            this.rdoRandom.AutoSize = true;
            this.rdoRandom.Checked = true;
            this.rdoRandom.Location = new System.Drawing.Point(38, 35);
            this.rdoRandom.Name = "rdoRandom";
            this.rdoRandom.Size = new System.Drawing.Size(159, 19);
            this.rdoRandom.TabIndex = 2;
            this.rdoRandom.TabStop = true;
            this.rdoRandom.Text = "Random Reference String";
            this.rdoRandom.UseVisualStyleBackColor = true;
            // 
            // rdoEnter
            // 
            this.rdoEnter.AutoSize = true;
            this.rdoEnter.Location = new System.Drawing.Point(231, 35);
            this.rdoEnter.Name = "rdoEnter";
            this.rdoEnter.Size = new System.Drawing.Size(141, 19);
            this.rdoEnter.TabIndex = 3;
            this.rdoEnter.Text = "Enter Reference String";
            this.rdoEnter.UseVisualStyleBackColor = true;
            this.rdoEnter.CheckedChanged += new System.EventHandler(this.rdoEnter_CheckedChanged);
            // 
            // txtReferenceString
            // 
            this.txtReferenceString.Location = new System.Drawing.Point(298, 94);
            this.txtReferenceString.Name = "txtReferenceString";
            this.txtReferenceString.ReadOnly = true;
            this.txtReferenceString.Size = new System.Drawing.Size(209, 23);
            this.txtReferenceString.TabIndex = 4;
            // 
            // btnStart
            // 
            this.btnStart.Location = new System.Drawing.Point(364, 247);
            this.btnStart.Name = "btnStart";
            this.btnStart.Size = new System.Drawing.Size(209, 23);
            this.btnStart.TabIndex = 5;
            this.btnStart.Text = "Calculate Page Faults";
            this.btnStart.UseVisualStyleBackColor = true;
            this.btnStart.Click += new System.EventHandler(this.btnStart_Click);
            // 
            // lstResult
            // 
            this.lstResult.FormattingEnabled = true;
            this.lstResult.ItemHeight = 15;
            this.lstResult.Location = new System.Drawing.Point(38, 222);
            this.lstResult.Name = "lstResult";
            this.lstResult.Size = new System.Drawing.Size(285, 154);
            this.lstResult.TabIndex = 6;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(38, 97);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(254, 15);
            this.label2.TabIndex = 7;
            this.label2.Text = "Reference String (please enter with no spaces): ";
            // 
            // frmPageReplacement
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(622, 424);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.lstResult);
            this.Controls.Add(this.btnStart);
            this.Controls.Add(this.txtReferenceString);
            this.Controls.Add(this.rdoEnter);
            this.Controls.Add(this.rdoRandom);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.txtFrameNum);
            this.Name = "frmPageReplacement";
            this.Text = "Page Replacement";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox txtFrameNum;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.RadioButton rdoRandom;
        private System.Windows.Forms.RadioButton rdoEnter;
        private System.Windows.Forms.TextBox txtReferenceString;
        private System.Windows.Forms.Button btnStart;
        private System.Windows.Forms.ListBox lstResult;
        private System.Windows.Forms.Label label2;
    }
}

