
namespace Samantha_Prichard_Bankers
{
    partial class Bankers
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
            this.lstFile = new System.Windows.Forms.ListBox();
            this.btnFile = new System.Windows.Forms.Button();
            this.lstAllocation = new System.Windows.Forms.ListBox();
            this.lstMax = new System.Windows.Forms.ListBox();
            this.lstAvailable = new System.Windows.Forms.ListBox();
            this.lstNeed = new System.Windows.Forms.ListBox();
            this.txtResources = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.txtOutput = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.txtProcess = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.btnReset = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // lstFile
            // 
            this.lstFile.FormattingEnabled = true;
            this.lstFile.ItemHeight = 15;
            this.lstFile.Location = new System.Drawing.Point(12, 12);
            this.lstFile.Name = "lstFile";
            this.lstFile.Size = new System.Drawing.Size(149, 124);
            this.lstFile.TabIndex = 30;
            // 
            // btnFile
            // 
            this.btnFile.Location = new System.Drawing.Point(615, 26);
            this.btnFile.Name = "btnFile";
            this.btnFile.Size = new System.Drawing.Size(102, 23);
            this.btnFile.TabIndex = 1;
            this.btnFile.Text = "Start";
            this.btnFile.UseVisualStyleBackColor = true;
            this.btnFile.Click += new System.EventHandler(this.btnFile_Click);
            // 
            // lstAllocation
            // 
            this.lstAllocation.FormattingEnabled = true;
            this.lstAllocation.ItemHeight = 15;
            this.lstAllocation.Location = new System.Drawing.Point(12, 196);
            this.lstAllocation.Name = "lstAllocation";
            this.lstAllocation.Size = new System.Drawing.Size(149, 124);
            this.lstAllocation.TabIndex = 32;
            // 
            // lstMax
            // 
            this.lstMax.FormattingEnabled = true;
            this.lstMax.ItemHeight = 15;
            this.lstMax.Location = new System.Drawing.Point(196, 196);
            this.lstMax.Name = "lstMax";
            this.lstMax.Size = new System.Drawing.Size(139, 124);
            this.lstMax.TabIndex = 3;
            // 
            // lstAvailable
            // 
            this.lstAvailable.FormattingEnabled = true;
            this.lstAvailable.ItemHeight = 15;
            this.lstAvailable.Location = new System.Drawing.Point(381, 196);
            this.lstAvailable.Name = "lstAvailable";
            this.lstAvailable.Size = new System.Drawing.Size(136, 124);
            this.lstAvailable.TabIndex = 4;
            // 
            // lstNeed
            // 
            this.lstNeed.FormattingEnabled = true;
            this.lstNeed.ItemHeight = 15;
            this.lstNeed.Location = new System.Drawing.Point(551, 196);
            this.lstNeed.Name = "lstNeed";
            this.lstNeed.Size = new System.Drawing.Size(149, 124);
            this.lstNeed.TabIndex = 5;
            // 
            // txtResources
            // 
            this.txtResources.Location = new System.Drawing.Point(486, 68);
            this.txtResources.Name = "txtResources";
            this.txtResources.Size = new System.Drawing.Size(100, 23);
            this.txtResources.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 169);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(105, 15);
            this.label1.TabIndex = 7;
            this.label1.Text = "Starting Allocation";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(196, 169);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(30, 15);
            this.label2.TabIndex = 8;
            this.label2.Text = "Max";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(381, 169);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(99, 15);
            this.label3.TabIndex = 9;
            this.label3.Text = "Starting Available";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(551, 169);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(79, 15);
            this.label4.TabIndex = 10;
            this.label4.Text = "Starting Need";
            // 
            // txtOutput
            // 
            this.txtOutput.Location = new System.Drawing.Point(209, 113);
            this.txtOutput.Name = "txtOutput";
            this.txtOutput.ReadOnly = true;
            this.txtOutput.Size = new System.Drawing.Size(508, 23);
            this.txtOutput.TabIndex = 11;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(209, 29);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(263, 15);
            this.label5.TabIndex = 12;
            this.label5.Text = "Process requesing resources: (Example: 1 for P1) ";
            // 
            // txtProcess
            // 
            this.txtProcess.Location = new System.Drawing.Point(486, 26);
            this.txtProcess.Name = "txtProcess";
            this.txtProcess.Size = new System.Drawing.Size(100, 23);
            this.txtProcess.TabIndex = 0;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(209, 71);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(237, 15);
            this.label6.TabIndex = 14;
            this.label6.Text = "Resource request: (Example: 1 0 2 for 1, 0, 2)";
            // 
            // btnReset
            // 
            this.btnReset.Location = new System.Drawing.Point(615, 71);
            this.btnReset.Name = "btnReset";
            this.btnReset.Size = new System.Drawing.Size(102, 23);
            this.btnReset.TabIndex = 15;
            this.btnReset.Text = "Reset Values";
            this.btnReset.UseVisualStyleBackColor = true;
            this.btnReset.Click += new System.EventHandler(this.btnReset_Click);
            // 
            // Bankers
            // 
            this.AcceptButton = this.btnFile;
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.btnReset);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.txtProcess);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.txtOutput);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.txtResources);
            this.Controls.Add(this.lstNeed);
            this.Controls.Add(this.lstAvailable);
            this.Controls.Add(this.lstMax);
            this.Controls.Add(this.lstAllocation);
            this.Controls.Add(this.btnFile);
            this.Controls.Add(this.lstFile);
            this.Name = "Bankers";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox lstFile;
        private System.Windows.Forms.Button btnFile;
        private System.Windows.Forms.ListBox lstAllocation;
        private System.Windows.Forms.ListBox lstMax;
        private System.Windows.Forms.ListBox lstAvailable;
        private System.Windows.Forms.ListBox lstNeed;
        private System.Windows.Forms.TextBox txtResources;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txtOutput;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox txtProcess;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Button btnReset;
    }
}

