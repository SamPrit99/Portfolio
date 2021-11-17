namespace Pritchard_GOLPartI
{
    partial class Pritchard_GOL
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
            this.radEmpty = new System.Windows.Forms.RadioButton();
            this.radPulsar = new System.Windows.Forms.RadioButton();
            this.btnCreateGrid = new System.Windows.Forms.Button();
            this.startLife = new System.Windows.Forms.Button();
            this.radBeacon = new System.Windows.Forms.RadioButton();
            this.radInfinite = new System.Windows.Forms.RadioButton();
            this.radRandom = new System.Windows.Forms.RadioButton();
            this.radFile = new System.Windows.Forms.RadioButton();
            this.panStats = new System.Windows.Forms.Panel();
            this.lstStats = new System.Windows.Forms.ListBox();
            this.fdOpenLife = new System.Windows.Forms.OpenFileDialog();
            this.btnUpload = new System.Windows.Forms.Button();
            this.btnSave = new System.Windows.Forms.Button();
            this.txtGenNumber = new System.Windows.Forms.TextBox();
            this.lblNum = new System.Windows.Forms.Label();
            this.fdSaveFile = new System.Windows.Forms.SaveFileDialog();
            this.panStats.SuspendLayout();
            this.SuspendLayout();
            // 
            // radEmpty
            // 
            this.radEmpty.AutoSize = true;
            this.radEmpty.Checked = true;
            this.radEmpty.Location = new System.Drawing.Point(720, 36);
            this.radEmpty.Name = "radEmpty";
            this.radEmpty.Size = new System.Drawing.Size(140, 20);
            this.radEmpty.TabIndex = 0;
            this.radEmpty.TabStop = true;
            this.radEmpty.Text = "Display Empty Grid";
            this.radEmpty.UseVisualStyleBackColor = true;
            // 
            // radPulsar
            // 
            this.radPulsar.AutoSize = true;
            this.radPulsar.Location = new System.Drawing.Point(720, 76);
            this.radPulsar.Name = "radPulsar";
            this.radPulsar.Size = new System.Drawing.Size(110, 20);
            this.radPulsar.TabIndex = 1;
            this.radPulsar.Text = "Display Pulsar";
            this.radPulsar.UseVisualStyleBackColor = true;
            // 
            // btnCreateGrid
            // 
            this.btnCreateGrid.BackColor = System.Drawing.Color.Red;
            this.btnCreateGrid.ForeColor = System.Drawing.SystemColors.ControlText;
            this.btnCreateGrid.Location = new System.Drawing.Point(866, 218);
            this.btnCreateGrid.Name = "btnCreateGrid";
            this.btnCreateGrid.Size = new System.Drawing.Size(102, 25);
            this.btnCreateGrid.TabIndex = 2;
            this.btnCreateGrid.Text = "Display Grid";
            this.btnCreateGrid.UseVisualStyleBackColor = false;
            this.btnCreateGrid.Click += new System.EventHandler(this.btnCreateGrid_Click);
            // 
            // startLife
            // 
            this.startLife.BackColor = System.Drawing.Color.Lime;
            this.startLife.Enabled = false;
            this.startLife.Location = new System.Drawing.Point(974, 218);
            this.startLife.Name = "startLife";
            this.startLife.Size = new System.Drawing.Size(102, 25);
            this.startLife.TabIndex = 3;
            this.startLife.Text = "Let Cells Live";
            this.startLife.UseVisualStyleBackColor = false;
            this.startLife.Click += new System.EventHandler(this.startLife_Click);
            // 
            // radBeacon
            // 
            this.radBeacon.AutoSize = true;
            this.radBeacon.Location = new System.Drawing.Point(720, 112);
            this.radBeacon.Name = "radBeacon";
            this.radBeacon.Size = new System.Drawing.Size(116, 20);
            this.radBeacon.TabIndex = 4;
            this.radBeacon.TabStop = true;
            this.radBeacon.Text = "Display Beacon";
            this.radBeacon.UseVisualStyleBackColor = true;
            // 
            // radInfinite
            // 
            this.radInfinite.AutoSize = true;
            this.radInfinite.Location = new System.Drawing.Point(720, 148);
            this.radInfinite.Name = "radInfinite";
            this.radInfinite.Size = new System.Drawing.Size(119, 20);
            this.radInfinite.TabIndex = 5;
            this.radInfinite.TabStop = true;
            this.radInfinite.Text = "Display Infinite";
            this.radInfinite.UseVisualStyleBackColor = true;
            // 
            // radRandom
            // 
            this.radRandom.AutoSize = true;
            this.radRandom.Location = new System.Drawing.Point(720, 183);
            this.radRandom.Name = "radRandom";
            this.radRandom.Size = new System.Drawing.Size(150, 20);
            this.radRandom.TabIndex = 6;
            this.radRandom.TabStop = true;
            this.radRandom.Text = "Display Random Grid";
            this.radRandom.UseVisualStyleBackColor = true;
            // 
            // radFile
            // 
            this.radFile.AutoSize = true;
            this.radFile.Location = new System.Drawing.Point(720, 223);
            this.radFile.Name = "radFile";
            this.radFile.Size = new System.Drawing.Size(85, 20);
            this.radFile.TabIndex = 7;
            this.radFile.TabStop = true;
            this.radFile.Text = "Open File";
            this.radFile.UseVisualStyleBackColor = true;
            // 
            // panStats
            // 
            this.panStats.Controls.Add(this.lstStats);
            this.panStats.Location = new System.Drawing.Point(866, 36);
            this.panStats.Name = "panStats";
            this.panStats.Size = new System.Drawing.Size(217, 132);
            this.panStats.TabIndex = 8;
            // 
            // lstStats
            // 
            this.lstStats.FormattingEnabled = true;
            this.lstStats.ItemHeight = 15;
            this.lstStats.Location = new System.Drawing.Point(3, 3);
            this.lstStats.Name = "lstStats";
            this.lstStats.Size = new System.Drawing.Size(207, 109);
            this.lstStats.TabIndex = 0;
            // 
            // fdOpenLife
            // 
            this.fdOpenLife.Filter = ".txt|";
            // 
            // btnUpload
            // 
            this.btnUpload.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(192)))), ((int)(((byte)(255)))));
            this.btnUpload.Location = new System.Drawing.Point(869, 249);
            this.btnUpload.Name = "btnUpload";
            this.btnUpload.Size = new System.Drawing.Size(99, 23);
            this.btnUpload.TabIndex = 9;
            this.btnUpload.Text = "Upload File";
            this.btnUpload.UseVisualStyleBackColor = false;
            this.btnUpload.Click += new System.EventHandler(this.btnUpload_Click);
            // 
            // btnSave
            // 
            this.btnSave.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(255)))), ((int)(((byte)(255)))), ((int)(((byte)(128)))));
            this.btnSave.Enabled = false;
            this.btnSave.Location = new System.Drawing.Point(974, 249);
            this.btnSave.Name = "btnSave";
            this.btnSave.Size = new System.Drawing.Size(102, 23);
            this.btnSave.TabIndex = 10;
            this.btnSave.Text = "Save File";
            this.btnSave.UseVisualStyleBackColor = false;
            this.btnSave.Click += new System.EventHandler(this.btnSave_Click);
            // 
            // txtGenNumber
            // 
            this.txtGenNumber.Location = new System.Drawing.Point(922, 304);
            this.txtGenNumber.Name = "txtGenNumber";
            this.txtGenNumber.Size = new System.Drawing.Size(100, 21);
            this.txtGenNumber.TabIndex = 11;
            // 
            // lblNum
            // 
            this.lblNum.AutoSize = true;
            this.lblNum.Location = new System.Drawing.Point(879, 285);
            this.lblNum.Name = "lblNum";
            this.lblNum.Size = new System.Drawing.Size(183, 16);
            this.lblNum.TabIndex = 12;
            this.lblNum.Text = "Choose  Number of Generations";
            // 
            // fdSaveFile
            // 
            this.fdSaveFile.CheckPathExists = false;
            this.fdSaveFile.Filter = ".txt|";
            // 
            // Pritchard_GOL
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1095, 422);
            this.Controls.Add(this.lblNum);
            this.Controls.Add(this.txtGenNumber);
            this.Controls.Add(this.btnSave);
            this.Controls.Add(this.btnUpload);
            this.Controls.Add(this.panStats);
            this.Controls.Add(this.radFile);
            this.Controls.Add(this.radRandom);
            this.Controls.Add(this.radInfinite);
            this.Controls.Add(this.radBeacon);
            this.Controls.Add(this.startLife);
            this.Controls.Add(this.btnCreateGrid);
            this.Controls.Add(this.radPulsar);
            this.Controls.Add(this.radEmpty);
            this.Font = new System.Drawing.Font("Modern No. 20", 7.8F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Name = "Pritchard_GOL";
            this.Text = "Samantha Pritchard - GOL Part II";
            this.panStats.ResumeLayout(false);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.RadioButton radEmpty;
        private System.Windows.Forms.RadioButton radPulsar;
        private System.Windows.Forms.Button btnCreateGrid;
        private System.Windows.Forms.Button startLife;
        private System.Windows.Forms.RadioButton radBeacon;
        private System.Windows.Forms.RadioButton radInfinite;
        private System.Windows.Forms.RadioButton radRandom;
        private System.Windows.Forms.RadioButton radFile;
        private System.Windows.Forms.Panel panStats;
        private System.Windows.Forms.ListBox lstStats;
        private System.Windows.Forms.OpenFileDialog fdOpenLife;
        private System.Windows.Forms.Button btnUpload;
        private System.Windows.Forms.Button btnSave;
        private System.Windows.Forms.TextBox txtGenNumber;
        private System.Windows.Forms.Label lblNum;
        private System.Windows.Forms.SaveFileDialog fdSaveFile;
    }
}

