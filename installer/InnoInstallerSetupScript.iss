; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{7F99D2C0-6EF4-473D-92DA-D91C7AD63AA2}
AppName=Media Manager UI
AppVersion=1.1
;AppVerName=Media Manager UI 1.1
AppPublisher=JMS
DefaultDirName={pf}\Media Manager UI
DisableProgramGroupPage=yes
OutputDir=C:\Users\Tekken\Documents\NetBeansProjects\MediaManager\installer
OutputBaseFilename=setup
SetupIconFile=C:\Users\Tekken\Downloads\Treetog-I-Video-File.ico
Compression=lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked
Name: "quicklaunchicon"; Description: "{cm:CreateQuickLaunchIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked; OnlyBelowVersion: 0,6.1

[Files]
Source: "C:\Users\Tekken\Documents\NetBeansProjects\MediaManager\installer\MediaManagerUIInstaller.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\Tekken\Documents\NetBeansProjects\MediaManager\dist\lib\*"; DestDir: "{app}\lib\"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{commonprograms}\Media Manager UI"; Filename: "{app}\MediaManagerUI.exe"
Name: "{commondesktop}\Media Manager UI"; Filename: "{app}\MediaManagerUI.exe"; Tasks: desktopicon
Name: "{userappdata}\Microsoft\Internet Explorer\Quick Launch\Media Manager UI"; Filename: "{app}\MediaManagerUI.exe"; Tasks: quicklaunchicon

[Run]
Filename: "{app}\MediaManagerUI.exe"; Description: "{cm:LaunchProgram,Media Manager UI}"; Flags: nowait postinstall skipifsilent
