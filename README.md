# LevelUpdater
The lightweight plugin checks for new versions, downloads updates, and installs them with minimal setup. Perfect for desktop apps built with Java (Swing, JavaFX, etc.).

# Key Features
✅ Seamless in-app update support

✅ Compatible with Swing, JavaFX, and other Java frameworks

✅ Secure download & version verification (checksum, HTTPS)

✅ Auto-check for new versions (on startup or schedule)

✅ Lightweight & easy to integrate (plug-and-play)

✅ Cross-platform support (Windows, macOS, Linux)


# Use Cases
- Java desktop applications needing auto-update functionality
- Enterprise software requiring version control
- Developer tools and utilities built with Java

# How to Use
<pre>LevelUpdater updater = new LevelUpdater();
updater.SetCurrentVersion("1.0.0");
updater.SetCheckUpdateURL("https://coedotzmagic.com/test-ver.txt");
updater.SetDownloadFileUrl("https://coedotzmagic.com/example.zip");
updater.SetDownloadPageUrl("https://coedotzmagic.com/");
updater.SetIsDirectDownload(true); // if false, direct to page download (not direct to file download)
updater.CheckForUpdate();  
</pre>

<b>in the file test-ver.txt</b>
<pre>1.2</pre>
you need set update url and newer version, fill the new version, if you fill current version, updater will showing "you are using the latest version".
